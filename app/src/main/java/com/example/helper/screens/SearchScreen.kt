package com.example.helper.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.example.helper.R

@Composable
fun SearchScreen(navController: NavController) {
    val searchResults = remember { mutableStateListOf<SearchItem>() }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchBar2(navController) { query ->
                searchResults.clear()
                if (query.isNotEmpty()) {
                    searchResults.addAll(generateSearchResults(query)) // Simulated search
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            if (searchResults.isEmpty()) {
                Text("No results found", fontSize = 18.sp, modifier = Modifier.padding(20.dp))
            } else {
                SearchResultCard(items = searchResults)
            }

//            SearchResultCard(items = searchResults)

            Button(onClick = { navController.popBackStack() }, modifier = Modifier.padding(20.dp)) {
                Text("Go Back")
            }
        }
    }
}

@Composable
fun SearchBar2(navController: NavController, onSearch: (String) -> Unit) {
    var searchText by remember { mutableStateOf("") }
    var animatedText by remember { mutableStateOf("") }
    val placeholderText = "Search Notes..."

    LaunchedEffect(Unit) {
        for (i in placeholderText.indices) {
            animatedText = placeholderText.substring(0, i + 1)
            delay(150)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Search Icon",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
                    .clickable(onClick = {
                        navController.popBackStack()
                    })
            )
            Spacer(modifier = Modifier.width(10.dp))

            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            BasicTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    onSearch(it) // Call search function when text changes
                },
                modifier = Modifier.weight(1f),
                singleLine = true,
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
fun SearchResultCard(
    modifier: Modifier = Modifier,
    items: List<SearchItem>
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.White)
            .padding(10.dp)
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(items.size) { index ->
                SearchResultItem(items[index])
            }
        }
    }
}

@Composable
fun SearchResultItem(item: SearchItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            Image(
                painter = painterResource(id = R.drawable.img3),
                contentDescription = null,
                modifier = Modifier
                    .size(110.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(
                    text = "Title: ${item.title}",
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
                )
                Text(
                    text = "Category: ${item.category}",
                    style = TextStyle(fontSize = 14.sp, color = Color.Gray)
                )
                Text(
                    text = "Description: ${item.description}",
                    style = TextStyle(fontSize = 16.sp)
                )
            }
        }
    }
}

data class SearchItem(
    val title: String,
    val category: String,
    val description: String
)

// Simulated search function
fun generateSearchResults(query: String): List<SearchItem> {
    val allItems = listOf(
        SearchItem("Kotlin Basics", "Programming", "Introduction to Kotlin"),
        SearchItem("Jetpack Compose", "Android", "Learn Jetpack Compose UI"),
        SearchItem("Firebase Database", "Backend", "Using Firebase with Android"),
        SearchItem("Data Structures", "Computer Science", "Introduction to DSA"),
        SearchItem("Machine Learning", "AI", "Basics of ML and AI"),
    )
    return if (query.isBlank()) allItems else allItems.filter { it.title.contains(query, ignoreCase = true) }
}


