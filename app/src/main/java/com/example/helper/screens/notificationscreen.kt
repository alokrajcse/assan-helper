package com.example.helper.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Notifications(modifier: Modifier = Modifier,navController: NavController) {

    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text("Notifications") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()

                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back btn")
                    }
                },
                actions = {

                }
            )
        }

    )
    { paddingValues ->
        Box(modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth())
        {
            Column(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()) {
                NotificationItem()
                NotificationItem()
                NotificationItem()
                NotificationItem()
                NotificationItem()
                NotificationItem()
                NotificationItem()
                NotificationItem()
                NotificationItem()
                NotificationItem()
                NotificationItem()
                NotificationItem()
            }


        }



    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationItem(modifier: Modifier = Modifier) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()




    Card(modifier=Modifier.padding(10.dp), onClick = {
        showBottomSheet = true

    }) {
        Row(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()) {
            Column {
                Text(
                    text = "New Notification",
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
                Text("Notification Description")
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            // Sheet content
            Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(15.dp)){
                NotificationDetails()
                Button(onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                        }
                    }
                }) {
                    Text("Okay")
                }
            }
        }
    }

}


@Composable
fun NotificationDetails(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxWidth())
    {
        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally){
            Text(modifier = Modifier.padding(10.dp),
                text = "New Notification",
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
            Text(
                text = "Hello everyone! This is a Notification Description to announce that notes are updated",
                modifier = Modifier.width(300.dp), // Center within 300dp width
                textAlign = TextAlign.Center
            )

        }
    }

}