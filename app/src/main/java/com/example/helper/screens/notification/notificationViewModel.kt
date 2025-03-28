package com.example.helper.screens.notification

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class notificationViewModel: ViewModel(){

    private val _notificationstate=MutableStateFlow(emptyList<Notification>())
    val notificationState: StateFlow<List<Notification>> = _notificationstate.asStateFlow()

    init {
        _notificationstate.update { sampleNotifications() }
    }
    fun refresh(){
        _notificationstate.update { sampleNotifications() }
    }
    fun removeItem(currentItem: Notification){
        _notificationstate.update {
            val mutableList=it.toMutableList()
            mutableList.remove(currentItem)
            mutableList
        }
    }

}



fun sampleNotifications()= listOf(
    Notification("New Notification", "Notification Description"),
    Notification("New Notification2", "Notification Description2"),
    Notification("New Notification3", "Notification Description3"),
    Notification("New Notification4", "Notification Description4"),
    Notification("New Notification5", "Notification Description5"),
    Notification("New Notification7", "Notification Description6"),


    )