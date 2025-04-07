package com.example.helper.screens.ClassNotes
import com.example.helper.R
import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.helper.data.noteItem
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun allNotesScreen(navController: NavController,modifier: Modifier = Modifier,  branchname:String?, semestername:String?) {


    var noteslist by remember {
        mutableStateOf(
            listOf(
                noteItem("Note1", "Compiler Design"),
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
                noteItem("Project Management", "Software Engineering")
            )
        )
    }


    var searchText by remember { mutableStateOf("") }
    var categories=noteslist.groupBy { it.noteCategory }
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    var filteredNotes = if (selectedCategory == null) {
        noteslist
    }
    else if (selectedCategory=="All"){
        noteslist
    }
    else {
        noteslist.filter { it.noteCategory == selectedCategory }
    }

    if (!searchText.isEmpty()){
        filteredNotes= filteredNotes.filter { it.noteTitle.contains(searchText, ignoreCase = true) }
    }
    else{

    }


    var text2 by remember { mutableStateOf("") }
    if (branchname=="FirstYear")
    {
        text2="First Year Notes"
    }
    else{
        text2="$branchname sem $semestername Notes"
    }
    Scaffold (topBar = {
        TopAppBar(

            title = {Text(text = text2, fontWeight = FontWeight.Bold, color = colorResource(R.color.darkcharcoal))}

            , colors = TopAppBarDefaults.smallTopAppBarColors(colorResource(R.color.indigo)),
            navigationIcon = {
                IconButton(onClick = {navController.popBackStack()}) {
                    Icon(Icons.Default.ArrowBack,contentDescription = "back btn", tint = Color.Black)

                }
            })


    },
        bottomBar = {
            Box(modifier = Modifier.fillMaxWidth().background(colorResource(R.color.lavenderblush)).padding(10.dp), contentAlignment = Alignment.TopStart){

                Column {
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

        ){ innerPadding ->

        val colorStops = arrayOf(
            0.0f to colorResource(R.color.indigo),
            0.2f to colorResource(R.color.lavenderblush),
            1f to colorResource(R.color.lavenderblush),
        )
//        Box(
//            modifier = Modifier
//                .requiredSize(200.dp)
//                .background(Brush.horizontalGradient(colorStops = colorStops))
//        )
        Box(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Brush.verticalGradient(colorStops = colorStops))){
            Column(modifier = Modifier.padding(10.dp)) {
                SearchBar3(searchText=searchText, onSearchTextChange = {searchText=it})
                Spacer(modifier = Modifier.height(10.dp))
                CategoryBar(noteslist=noteslist, selectedCategory = selectedCategory, onCategorySelected = { selectedCategory = it })
                Spacer(modifier = Modifier.height(5.dp))
                NotesGrid(navController,noteslist=filteredNotes)

            }

        }

    }

}


@Composable
fun SearchBar3(searchText: String, onSearchTextChange: (String)-> Unit)
{
//    var searchText by remember { mutableStateOf("") }
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
                onValueChange = onSearchTextChange,
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
fun NotesGrid(navController: NavController,modifier: Modifier = Modifier, noteslist: List<noteItem>) {
    val itemsList = (0..15).toList()

    val groupedNotes = noteslist.groupBy { it.noteCategory }


    val itemModifier = Modifier
        .border(1.dp, Color.Blue)
        .width(80.dp)
        .wrapContentSize()




    GroupedNotesList(navController,noteslist)


//    LazyVerticalGrid(
//        columns = GridCells.Fixed(2),
//        horizontalArrangement = Arrangement.spacedBy(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//
//
//
//
//
////        items(150) {
////            NoteItem(navController = navController)
////        }
////
////        item {
////            Text("Single item", itemModifier)
////        }
//    }

}


@Composable
fun CategoryBar(modifier: Modifier = Modifier, noteslist: List<noteItem>,  selectedCategory: String?, // passed from parent
                onCategorySelected: (String?) -> Unit ) {
    val subjects = listOf("compiler design", "computer graphics", "Iot", "Software Engineering")

    var currentcategory by remember { mutableStateOf("") }
    val groupedCategories = noteslist.groupBy { it.noteCategory }
    val categories = listOf("All") + groupedCategories.keys.toList()

    var selectedCategory by remember { mutableStateOf<String?>(null) }

    val filteredNotes = if (selectedCategory == null) {
        noteslist
    } else {
        noteslist.filter { it.noteCategory == selectedCategory }
    }
    LazyRow {

        categories.forEach{ category ->
            item{
                Card(modifier = Modifier.padding(5.dp).clickable {
                    onCategorySelected(category)
                    currentcategory=category
                }, border = BorderStroke(1.dp, Color.Black), colors = CardDefaults.cardColors(containerColor =

                if (category==currentcategory){
                    colorResource(R.color.teal_200)
                }
                        else{
                    colorResource(R.color.lightgray)
                        })


                ) {
                    Text(
                        text = category, fontWeight = FontWeight.Bold, // Corrected: Use 'subject' directly
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(RoundedCornerShape(10.dp))
                        , color = Color.Black
                    )
                }
            }
        }

//        items(subjects) { subject -> // Corrected: Single parameter 'subject'
//            Card(modifier = Modifier.padding(5.dp), border = BorderStroke(1.dp, Color.Black), colors = CardDefaults.cardColors(containerColor = colorResource(R.color.lightgray))) {
//                Text(
//                    text = subject, fontWeight = FontWeight.Bold, // Corrected: Use 'subject' directly
//                    modifier = Modifier
//                        .padding(10.dp)
//                        .clip(RoundedCornerShape(10.dp))
//                )
//            }
//        }
    }
}



@Composable
fun NoteItem(note: noteItem, navController: NavController, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .clickable {
                navController.navigate("notesdetailscreen")
            },
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.white))
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.notes),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = note.noteTitle,
                modifier = Modifier.padding(5.dp),
                color = colorResource(R.color.darkcharcoal),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
fun GroupedNotesList(navController: NavController, notesList: List<noteItem>) {
    val groupedNotes = notesList.groupBy { it.noteCategory }

    LazyColumn {
        groupedNotes.forEach { (category, notes) ->
            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

                    Text(
                        text = category,
                        fontWeight = FontWeight.ExtraBold,
                        color = colorResource(R.color.darkcharcoal),
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 22.sp,


                    )
                }

            }

            val rows = notes.chunked(2)
            items(rows) { rowNotes ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    rowNotes.forEach { note ->
                        NoteItem(
                            note = note,
                            navController = navController,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    // Add empty space if row has only 1 item
                    if (rowNotes.size < 2) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}


