package com.example.helper.screens.ClassNotes
import com.example.helper.R
import android.graphics.drawable.Icon
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun allNotesScreen(navController: NavController,modifier: Modifier = Modifier) {

    Scaffold (topBar = {
        TopAppBar(title = {Text("CSE sem 6 Notes")}, colors = TopAppBarDefaults.smallTopAppBarColors(colorResource(R.color.teal_200)),
            navigationIcon = {
                IconButton(onClick = {navController.popBackStack()}) {
                    Icon(Icons.Default.ArrowBack,contentDescription = "back btn")

                }
            })


    },
        ){ innerPadding ->

        Box(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(colorResource(R.color.teal_200))){
            Column(modifier = Modifier.padding(10.dp)) {
                SearchBar3()
                CategoryBar()
                NotesGrid(navController)

            }

        }

    }

}


@Composable
fun SearchBar3()
{
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
            .clickable {

            } // Fixed clickable position
            .padding(horizontal = 16.dp, vertical = 12.dp)
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
                onValueChange = { searchText=it },
                modifier = Modifier.weight(1f),
                singleLine = true,
                enabled = true,
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
fun NotesGrid(navController: NavController,modifier: Modifier = Modifier) {
    val itemsList = (0..15).toList()

    val itemModifier = Modifier
        .border(1.dp, Color.Blue)
        .width(80.dp)
        .wrapContentSize()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(150) {
            NoteItem(navController = navController)
        }

        item {
            Text("Single item", itemModifier)
        }
    }

}

@Composable
fun NoteItem(navController: NavController,modifier: Modifier = Modifier) {


    Card(modifier = Modifier
        .padding(10.dp)
        .clickable(onClick = {
            navController.navigate("notesdetailscreen")


        }),
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.white))

        ) {
        Column(
            modifier
                .padding(10.dp)
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(R.drawable.notes), contentDescription = null, modifier = Modifier.size(100.dp))
            Text(text = "Notes", modifier= Modifier.padding(5.dp))
        }
    }

}

@Composable
fun CategoryBar(modifier: Modifier = Modifier) {
    val subjects = listOf("compiler design", "computer graphics", "Iot", "Software Engineering")

    LazyRow {
        items(subjects) { subject -> // Corrected: Single parameter 'subject'
            Card(modifier = Modifier.padding(5.dp)) {
            Text(
                text = subject, // Corrected: Use 'subject' directly
                modifier = Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            }
        }
    }
}