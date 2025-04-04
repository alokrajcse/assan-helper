package com.example.helper.screens.registration

import com.example.helper.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun loginScreen(navController: NavController,modifier: Modifier = Modifier) {

    Scaffold { innerpadding->
        Box(modifier = Modifier.padding(innerpadding).fillMaxSize())
        {
            Column(modifier = Modifier.fillMaxSize().padding(25.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

                Image(painter = painterResource(R.drawable.templogo),contentDescription = "temp logo", modifier = Modifier.weight(.9f))


                Row(modifier = Modifier.weight(.2f).fillMaxSize(), verticalAlignment = Alignment.Bottom) {

                    Button(modifier = Modifier.fillMaxWidth()
                        .padding(10.dp), onClick = {navController.navigate("home")},
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.pink)),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Text(text = "Continue with College Email", modifier = Modifier.padding(10.dp))

                    }
                }


            }


        }
    }


}