package com.example.helper.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController  // Import this
import kotlinx.coroutines.delay
import com.example.helper.R

@Composable
fun SearchScreen(navController: NavController) {

    Scaffold {
        paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchBar2()
            SearchResultCard()

            Text("Search Screen", fontSize = 24.sp, modifier = Modifier.padding(20.dp))
            Button(onClick = { navController.popBackStack() }, modifier = Modifier.padding(20.dp)) {
                Text("Go Back")
            }
        }
    }

}

@Composable
fun SearchBar2() {
    var searchText by remember { mutableStateOf("") }
    var animatedText by remember { mutableStateOf("") }
    val placeholderText = "Search Notes..."

    LaunchedEffect(Unit) {
        for (i in placeholderText.indices) {
            animatedText = placeholderText.substring(0, i + 1)
            delay(150) // Fixed import issue
        }

    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.White)

            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            BasicTextField(
                value = searchText,
                onValueChange = { /* Prevent text editing */ },
                modifier = Modifier.weight(1f),
                singleLine = true,
                enabled = false,
                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                decorationBox = { innerTextField ->
                    if (searchText.isEmpty()) {
                        Text(animatedText, color = Color.Gray)
                    }
                    innerTextField()
                }
            )
        }
    }
}


@Composable
fun SearchResultCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.White)
            .padding(20.dp)
    ){

        Column {
            Card {
            Row() {
                Card(modifier = Modifier.padding(15.dp)) {
                    Image(painter = painterResource(id = R.drawable.img3), contentDescription = null, modifier = Modifier.height(110.dp))
                }

                Spacer(modifier = Modifier.width(10.dp))
                Column (modifier = Modifier.padding(10.dp)){
                    Text("Title: Mera Pyara Bhaalu", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp))
                    Text("Category: Love",style = TextStyle( fontSize = 14.sp, color = Color.Gray))
                    Text("Description: Bhaalu🐻 loves❤️ Billu😺",style = TextStyle( fontSize = 16.sp,))
                }

            }
            }
        }
    }

}