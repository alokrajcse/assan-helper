package com.example.helper.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.helper.R
import androidx.compose.ui.text.TextStyle



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(modifier: Modifier = Modifier,navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(title = { Text("Profile") },
            navigationIcon = {
                IconButton(onClick ={navController.popBackStack()} ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back btn")
                }
            },
            actions = {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit Profile")
                }
            }
        )
    }) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxWidth(), contentAlignment = Alignment.Center){
            Card(modifier = Modifier.padding(10.dp)) {

                Column(modifier=Modifier.padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painter = painterResource(R.drawable.img4), contentDescription = null,
                        modifier= Modifier.size(128.dp)
                            .clip(CircleShape).border(5.dp, Color.Red,CircleShape))

                    Text(
                        text = "Bhaalu",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = Color.Gray
                        )
                    )
                    Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(5.dp)) {
                            Text(text = "120")
                            Text(text = "Followers")
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(5.dp)) {
                            Text(text = "120")
                            Text(text = "Following")
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(5.dp)) {
                            Text(text = "12")
                            Text(text = "Contributions")
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))


                    Row {
                        Text("About Me")
                    }
                    Row {
                        Text(text = "College:", style = TextStyle(fontWeight=FontWeight.Bold), fontSize = 18.sp)
                        Text(" Silicon University")
                    }
                    Row {
                        Text(text = "Branch:", style = TextStyle(fontWeight=FontWeight.Bold), fontSize = 18.sp)
                        Text(" ECE")
                    }
                    Row {
                        Text(text = "Semester:", style = TextStyle(fontWeight=FontWeight.Bold), fontSize = 18.sp)
                        Text(" 5th")
                    }
                    Button(onClick = {}, modifier = Modifier.padding(15.dp)) {
                        Text(text = "Your Notes", modifier = Modifier.padding(5.dp))
                    }

                }

            }



        }
    }

}