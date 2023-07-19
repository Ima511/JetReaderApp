package com.example.jetreaderapp.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetreaderapp.model.MUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class LoginScreenViewModel:ViewModel() {
   // val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

   fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit)
   = viewModelScope.launch{
       try {
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful){
                        Log.d("TAG","signInWithEmailAndPassword: Yayayaya${task.result.toString()}")
                      // TODO("take them home")
                        home()
                    }else{
                        Log.d("TAG","signInWithEmailAndPassword: ${task.result.toString()}")
                    }
                }
       }catch (ex: Exception){
            Log.d("FB","signInWithEmailAndPassword: ${ex.localizedMessage}")
       }

    }

    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        home: () -> Unit
    ){
        if(_loading.value == false){
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {task ->
                    if (task.isSuccessful){
                        // me@gmail.com
                        val displayName = task.result.user?.email?.substringBefore('@')
                        createUser(displayName)
                        home()
                    }else{
                        Log.d("TAG","createUserWithEmailAndPassword: ${task.result.toString()}")
                    }
                    _loading.value = false
                }
        }
    }

    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid
        val user = MUser(userId = userId.toString(),
        displayName = displayName.toString(),
        avatarUrl = "",
        quote = "Life is hate",
        professoin = "Android Developer",
            id = null
        ).toMap()

//        user["user_id"] = userId.toString()
//        user["display_name"] = displayName.toString()
        FirebaseFirestore.getInstance().collection("users")
            .add(user)
    }
}