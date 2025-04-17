package com.example.helper.screens.ClassNotes
import com.example.helper.R
import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun branchSelectScreen(navController: NavController,modifier: Modifier = Modifier,) {
    val colorStops = arrayOf(
        0.0f to colorResource(R.color.darkpurple),
        0.2f to colorResource(R.color.lavenderblush),
        1f to colorResource(R.color.lavenderblush),
    )

    var branchlist=listOf("FirstYear","CSE","CST","CEN","ECE","EEE","EIE")

    Scaffold(topBar = { TopAppBar(
        title = {Text("Class Notes", fontWeight = FontWeight.Bold, color = Color.White)}
    , navigationIcon = {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back btn", tint = Color.White)
        }
        }
        , colors = TopAppBarDefaults.smallTopAppBarColors(colorResource(R.color.darkpurple)))  },

         bottomBar = {
            Box(modifier = Modifier.fillMaxWidth().background(colorResource(R.color.lavenderblush)).padding(10.dp), contentAlignment = Alignment.TopStart){

                Column {
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    ) { innerPadding ->

        Box (modifier= Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Brush.verticalGradient(colorStops = colorStops))){

            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                item{

                branchlist.forEach { branchname->
                    branchItem(navController,branchname)
                  }
                }
            }

        }
    }

}


@Composable
fun branchItem(navController: NavController,branchname:String,modifier: Modifier = Modifier) {
    Card(modifier = Modifier.padding(10.dp).fillMaxWidth()
        .clickable(onClick = {
            if (branchname=="FirstYear") {
                var semestername=null
                navController.navigate("allnotesscreen/$branchname/$semestername")

            }
            else
            {
                navController.navigate("semesterselectscreen/$branchname")
            }

        }),
        border = CardDefaults.outlinedCardBorder(true),
        colors = CardDefaults.cardColors(colorResource(R.color.white)),
        elevation = CardDefaults.cardElevation(10.dp)
        ) {
        Text(text = branchname, modifier = Modifier.padding(30.dp), fontWeight = FontWeight.Bold, color = Color.Black)

    }

}