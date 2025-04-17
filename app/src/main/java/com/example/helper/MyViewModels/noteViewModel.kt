package com.example.helper.MyViewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.helper.data.noteItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyViewModel: ViewModel(){

    private val _text = MutableStateFlow("Hellow from viewmodel")
    val text= _text.asStateFlow()

    private val _items = mutableStateListOf<noteItem>( noteItem("Note1", "Compiler Design"),
        noteItem("Note2", "Compiler Design"),
        noteItem("Note3", "Computer Graphics"),
        noteItem("Note4", "Computer Graphics"),
        noteItem("Note5", "Computer Graphics"),
        noteItem("Note6", "Cloud Computing"),
        noteItem("Note7", "Cloud Computing"),
        noteItem("Note8", "Cloud Computing"),
        noteItem("Note9", "IoT"),
        noteItem("Note10", "IoT"),
        noteItem("Note11", "IoT"),
        noteItem("Note12", "Software Engineering"),
        noteItem("Note13", "Software Engineering"),
        noteItem("Note14", "Software Engineering"),
        noteItem("Lexical Analysis", "Compiler Design"),
        noteItem("Parsing Techniques", "Compiler Design"),
        noteItem("Intermediate Code Gen", "Compiler Design"),
        noteItem("2D Transformations", "Computer Graphics"),
        noteItem("3D Modeling", "Computer Graphics"),
        noteItem("Lighting & Shading", "Computer Graphics"),
        noteItem("Intro to Cloud", "Cloud Cloud"),
        noteItem("Cloud Deployment Models", "Cloud Cloud"),
        noteItem("Cloud Security", "Cloud Cloud"),
        noteItem("IoT Architecture", "IoT"),
        noteItem("IoT Protocols", "IoT"),
        noteItem("Sensor Networks", "IoT"),
        noteItem("Agile Methodology", "Software Engineering"),
        noteItem("Design Patterns", "Software Engineering"),
        noteItem("Project Management", "Software Engineering"))

    val notelist: List<noteItem> = _items




    fun updateText(newText: String){
        _text.value=newText

    }
}