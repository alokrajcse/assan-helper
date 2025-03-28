package com.example.helper.screens.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.helper.screens.NotificationDetails
import kotlinx.coroutines.launch

/**
 * Composable that represents a single email message card.
 *
 * @param emailMessage The email message to display.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationCard(notification: Notification) {

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

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

    ListItem(
        modifier = Modifier.clip(MaterialTheme.shapes.small)
            .clickable(onClick =
            {
                showBottomSheet = true

            }),
        headlineContent = {
            Text(
                notification.title,
                style = MaterialTheme.typography.titleMedium
            )
        },
        supportingContent = {
            Text(
                notification.description,
                style = MaterialTheme.typography.bodySmall
            )
        },
        leadingContent = {
            Icon(
                Icons.Filled.Person,
                contentDescription = "person icon",
                Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(10.dp)
            )
        }
    )
}
