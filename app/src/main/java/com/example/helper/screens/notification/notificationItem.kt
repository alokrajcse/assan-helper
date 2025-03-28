package com.example.helper.screens.notification

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel



@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun NotificationDisp(notificationViewModel: notificationViewModel = viewModel()) {
    // Collect the state of messages from the view model
    val notifications by notificationViewModel.notificationState.collectAsState()


            LazyColumn(
                modifier = Modifier

                    .fillMaxSize(),
                contentPadding = PaddingValues(vertical = 12.dp),
            ) {
                itemsIndexed(
                    items = notifications,
                    // Provide a unique key based on the email content
                    key = { _, item -> item.hashCode() }
                ) { _, emailContent ->
                    // Display each email item
                    NotificationItem(emailContent, onRemove = notificationViewModel::removeItem)
                }
            }

}
