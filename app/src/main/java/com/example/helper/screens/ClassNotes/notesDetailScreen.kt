package com.example.helper.screens.ClassNotes

import android.widget.ImageButton
import androidx.compose.foundation.BorderStroke
import com.example.helper.R

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun notesDetailScreen(navController: NavController,modifier: Modifier = Modifier) {

    val colorStops = arrayOf(
        0.0f to colorResource(R.color.indigo),
        0.2f to colorResource(R.color.lavenderblush),
        1f to colorResource(R.color.lavenderblush),
    )

    Scaffold(topBar = {
        TopAppBar(title = {Text("Note Details", fontWeight = FontWeight.Bold, color = Color.Black)}, navigationIcon = {
            IconButton(onClick = {navController.navigateUp()}) {
                Icon(Icons.Default.ArrowBack,contentDescription = "back btn", tint = Color.Black)
            }
        }, actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Share,contentDescription = null, tint = Color.Black)
            }
        },
            colors = TopAppBarDefaults.smallTopAppBarColors(colorResource(R.color.indigo)))
    },
        bottomBar = {
            Box(modifier = Modifier.fillMaxWidth().background(colorResource(R.color.lavenderblush)).padding(10.dp), contentAlignment = Alignment.TopStart){

                Column {

                    Button(onClick = {}, modifier = Modifier.fillMaxWidth()
                        ,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.indigo))) {
                        Text("Download",modifier= Modifier.padding(5.dp), fontWeight = FontWeight.Bold, color = Color.Black)
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                }


            }


        }


    ) { innerpadding ->

        Box(modifier = Modifier.padding(innerpadding).fillMaxSize().background(Brush.verticalGradient(colorStops = colorStops))){

            Column {

                LazyColumn(modifier = Modifier.padding(10.dp).fillMaxWidth()) {

                    item{

                        Row(modifier= Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            Card(modifier= Modifier.padding(10.dp).fillMaxWidth(), border = BorderStroke(1.dp,Color.Black), colors = CardDefaults.cardColors(Color.White), elevation = CardDefaults.cardElevation(5.dp,5.dp)) {

                                Row (modifier= Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                                    Image(painter = painterResource(R.drawable.notes),contentDescription = null, modifier = Modifier.padding(20.dp).height(200.dp))
                                }


                            }
                        }
                        Row(modifier= Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.Start) {


                                Row (modifier= Modifier.weight(.7f),horizontalArrangement = Arrangement.Start){
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                                        IconButton(onClick = {}) {
                                            Image(painter = painterResource(R.drawable.like),contentDescription = null, modifier = Modifier.padding(5.dp))
                                        }
                                        Text("243", color = Color.Black)
                                        Text("Likes", color = Color.Black)

                                    }

                                    Spacer(modifier = Modifier.width(10.dp))

                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                                        IconButton(onClick = {}) {
                                            Image(painter = painterResource(R.drawable.comments),contentDescription = null,modifier = Modifier.padding(5.dp))
                                        }
                                        Text("243", color = Color.Black)
                                        Text("Comments", color = Color.Black)
                                    }

                                    Spacer(modifier = Modifier.width(10.dp))

                                }









//                            Card(modifier= Modifier.padding(10.dp)) {
//                                IconButton(onClick = {}) {
//                                    Image(painter = painterResource(R.drawable.like),contentDescription = null)
//                                }
//                                Text(text = "Like", modifier = Modifier.padding(10.dp))
//                            }
//                            Card(modifier= Modifier.padding(10.dp)) {
//                                Text("Comment", modifier = Modifier.padding(10.dp))
//                            }
//                            Card(modifier= Modifier.padding(10.dp)) {
//                                Text("Share", modifier = Modifier.padding(10.dp))
//                            }
                        }

                        Text("Software Engineering Notes ", modifier = Modifier.padding(10.dp), fontWeight = FontWeight.Bold, fontSize = 22.sp, color = Color.Black)
                        Text("Description ", modifier = Modifier.padding(10.dp), color = Color.Gray)
                        Row {

                            Text("Uploaded by: ", modifier = Modifier.padding(10.dp), fontWeight = FontWeight.Bold, color = Color.Black)
                            Text("ANJALI BHARTI ", modifier = Modifier.padding(10.dp), color = Color.Black)
                        }
                        Row {

                            Text("Modules : ", modifier = Modifier.padding(10.dp), fontWeight = FontWeight.Bold, color = Color.Black)
                            Text("1,2,3 ", modifier = Modifier.padding(10.dp), color = Color.Black)
                        }
                        Row {

                            Text("College Name: ", modifier = Modifier.padding(10.dp), fontWeight = FontWeight.Bold, color = Color.Black)
                            Text("Silicon University ", modifier = Modifier.padding(10.dp), color = Color.Black)
                        }
                        Row {

                            Text("Branch: ", modifier = Modifier.padding(10.dp), fontWeight = FontWeight.Bold, color = Color.Black)
                            Text("ECE ", modifier = Modifier.padding(10.dp), color = Color.Black)
                        }

                        Row {

                            Text("Semester: ", modifier = Modifier.padding(10.dp), fontWeight = FontWeight.Bold, color = Color.Black)
                            Text("6th ", modifier = Modifier.padding(10.dp), color = Color.Black)
                        }

                    }


                }





            }


        }

    }

}