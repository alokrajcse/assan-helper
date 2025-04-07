package com.example.helper.screens.ClassNotes

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.helper.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun semesterSelectScreen(navController: NavController,modifier: Modifier = Modifier,branchname:String?) {
    val colorStops = arrayOf(
        0.0f to colorResource(R.color.indigo),
        0.2f to colorResource(R.color.lavenderblush),
        1f to colorResource(R.color.lavenderblush),
    )

    var semesterlist=listOf("3rd","4th","5th","6th","7th","8th")

    Scaffold(topBar = { TopAppBar(
        title = {Text("Select Semester for $branchname", fontWeight = FontWeight.Bold, color = Color.Black)}
        , navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back btn", tint = Color.Black)
            }
        }
    , colors = TopAppBarDefaults.smallTopAppBarColors(colorResource(R.color.indigo)))  },


        bottomBar = {
            Box(modifier = Modifier.fillMaxWidth().background(colorResource(R.color.lavenderblush)).padding(10.dp), contentAlignment = Alignment.TopStart){

                Column {
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }) { innerPadding ->

        Box (modifier= Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Brush.verticalGradient(colorStops = colorStops))){

            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                item{
                    semesterlist.forEach { semestername->
                        semesterItem(navController,semestername=semestername,branchname=branchname)
                    }
                }
            }

        }
    }

}


@Composable
fun semesterItem(navController: NavController,semestername:String,modifier: Modifier = Modifier,branchname:String?) {
    Card(modifier = Modifier.padding(10.dp).fillMaxWidth()
        .clickable(onClick = {
            navController.navigate("allnotesscreen/$branchname/$semestername")

        }),
        border = CardDefaults.outlinedCardBorder(true),
        colors = CardDefaults.cardColors(colorResource(R.color.white)),
        elevation = CardDefaults.cardElevation(10.dp)) {
        Text(text = semestername, modifier = Modifier.padding(30.dp), fontWeight = FontWeight.Bold, color = Color.Black)

    }

}