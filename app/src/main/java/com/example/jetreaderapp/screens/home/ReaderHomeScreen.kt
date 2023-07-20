package com.example.jetreaderapp.screens.home

import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.jetreaderapp.components.FABContent
import com.example.jetreaderapp.components.ListCard
import com.example.jetreaderapp.components.ReaderAppBar
import com.example.jetreaderapp.components.TitleSection
import com.example.jetreaderapp.model.MBook
import com.example.jetreaderapp.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth


@Preview()
@Composable
fun Home(navController: NavController = NavController(LocalContext.current)) {

    Scaffold(topBar = {
        ReaderAppBar(title = "A.Reader", navController = navController)
    },
        floatingActionButton = {
            FABContent{}
        }) {
        // Content
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            // home content
            HomeContent(navController = navController)
        }
    }

}

@Composable
fun HomeContent(navController: NavController){
    val listOfBooks = listOf(
        MBook("dadfa","Hello again","All of us", null),
        MBook("sdfa","Hello ","Abhay", null),
        MBook("adfa"," again","Kumar", null),
        MBook("dfds","Never again","Jha", null),
        MBook("dadjlkjlkfa","stle ","Name", null),
        MBook("dkjlkadfa","like again","Humbhi", null),
        MBook("dkjkdfa","s again","Binsuriu", null),
        MBook("sdsdadfa","ahaabhya","Aofaat", null),
        MBook("dsdfadfa","antarma","Bekashish", null),
        MBook("dsfdadfa","Bengail Ai","Cuddhish", null),
    )
    val email = FirebaseAuth.getInstance().currentUser?.email
    val currentUserName = if (!email.isNullOrEmpty()){
        email.split('@')[0]
    }else{
        "N/A"
    }
    Column(Modifier.padding(2.dp),
        verticalArrangement = Arrangement.Top) {
        Row(modifier = Modifier.align(alignment = Alignment.Start)) {
            TitleSection(label = "Your reading \n " + " activity right now")
            Spacer(modifier = Modifier.fillMaxWidth(0.7f))
            Column {
                IconButton(onClick = {
                    navController.navigate(ReaderScreens.ReaderStatsScreen.name)
                }) {
                    Icon(imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Profile",
                        modifier = Modifier.size(45.dp),
                        tint = MaterialTheme.colors.secondaryVariant
                    )
                }
                Text(text = currentUserName, modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.overline,
                    color = Color.Red,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
                Divider()
            }

        }
        ReadingRightNowArea(books = listOf() ,
            navController = navController)
        
        TitleSection(label = "Reading List")

        BookListArea(listOfBooks = listOfBooks,
                     navController = navController)
    }
}

@Composable
fun BookListArea(listOfBooks: List<MBook>,
                 navController: NavController) {
        HorizontalScrollableComponent(listOfBooks){
            Log.d("TAG","BookListArea: ${it}")
            /*Todo :- Handle and describe the functionality of onCardPressed Click.
            ->> when we click on onCardPress the user should navigate to the details screen.
            * */
        }
}

@Composable
fun HorizontalScrollableComponent(listOfBooks: List<MBook>,
            onCardPressed: (String) -> Unit) {
        val scrollState = rememberScrollState()
    
    Row(modifier = Modifier
        .fillMaxWidth()
        .heightIn(280.dp)
        .horizontalScroll(scrollState)) {
            for (book in listOfBooks){
                ListCard(book){
                    onCardPressed(it)
                }
            }
    }
}

@Composable
fun ReadingRightNowArea(books: List<MBook>, navController: NavController){
        ListCard()

}









