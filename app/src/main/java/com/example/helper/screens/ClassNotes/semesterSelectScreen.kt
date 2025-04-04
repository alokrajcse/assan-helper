package com.example.helper.screens.ClassNotes

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun semesterSelectScreen(navController: NavController,modifier: Modifier = Modifier) {

    var semesterlist=listOf("3rd","4th","5th","6th","7th","8th")

    Scaffold(topBar = { TopAppBar(
        title = {Text("Class Notes")}
        , navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back btn")
            }
        }
    )  },

        ) { innerPadding ->

        Box (modifier= Modifier
            .padding(innerPadding)
            .fillMaxSize()){

            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                item{
                    semesterlist.forEach { semestername->
                        semesterItem(navController,semestername)
                    }
                }
            }

        }
    }

}


@Composable
fun semesterItem(navController: NavController,semestername:String,modifier: Modifier = Modifier) {
    Card(modifier = Modifier.padding(10.dp).fillMaxWidth()
        .clickable(onClick = {
            navController.navigate("allnotesscreen")

        })) {
        Text(text = semestername, modifier = Modifier.padding(30.dp), fontWeight = FontWeight.Bold)

    }

}