package com.example.jetreaderapp.screens.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.jetreaderapp.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Home(navController: NavHostController) {

    Scaffold(topBar = {
        ReaderAppBar(title = "A.Reader", navController = navController)
    },
        floatingActionButton = {
            FABContent{}
        }) {
        // Content
        Surface(modifier = Modifier.fillMaxSize()) {
            // home content
        }
    }

}

@Composable
fun ReaderAppBar(
    title: String,
    showProfile: Boolean = true,
    navController: NavController
){
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (showProfile){
                    Icon(imageVector = Icons.Default.Person,
                        contentDescription ="Logo Icon",
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .scale(0.9f)
                    )
                }
                Text(text = title,
                    color = Color.Red.copy(alpha = 0.7f),
                    style = TextStyle(fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,))
                Spacer(modifier = Modifier.width(150.dp))
            }
        },
        actions ={
            IconButton(onClick = { FirebaseAuth.getInstance().signOut()
                .run {
                    navController.navigate(ReaderScreens.LoginScreen.name)
                }
            }) {
                Icon(imageVector = Icons.Filled.Logout,
                    contentDescription = "Log out")
            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}

@Composable
fun FABContent(onTap: () -> Unit) {
    FloatingActionButton(onClick = {onTap()},
        shape = RoundedCornerShape(50.dp),
        backgroundColor = Color(0xFF92CBDF)) {
        Icon(imageVector = Icons.Default.Add,
            contentDescription = "Add a Book",
            tint = Color.White)
    }
}
