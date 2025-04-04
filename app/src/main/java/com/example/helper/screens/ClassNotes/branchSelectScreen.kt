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
fun branchSelectScreen(navController: NavController,modifier: Modifier = Modifier) {

    var branchlist=listOf("FirstYear","CSE","CST","CEN","ECE","EEE","EIE")

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
            navController.navigate("semesterselectscreen")

        })) {
        Text(text = branchname, modifier = Modifier.padding(30.dp), fontWeight = FontWeight.Bold)

    }

}