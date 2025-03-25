package com.example.helper.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.helper.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent { route ->
                coroutineScope.launch { drawerState.close() }
                navController.navigate(route)
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Study ASSAN") },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                if (drawerState.isClosed) drawerState.open() else drawerState.close()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* Handle notification click */ }) {
                            Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                        }
                    }
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .padding(paddingValues)
            ) {
                item {
                    Header()
                    Spacer(modifier = Modifier.height(16.dp))
                    SearchBar(navController,paddingValues)
                    Spacer(modifier = Modifier.height(16.dp))
                    Carousel()
                    Spacer(modifier = Modifier.height(16.dp))
                    Tools()
                    Spacer(modifier = Modifier.height(16.dp))
                    Carousel()
                    Spacer(modifier = Modifier.height(16.dp))
                    Cards()
                }
            }
        }
    }
}

@Composable
fun SearchBar(navController: NavController,paddingValues: PaddingValues) {
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
            .clickable { navController.navigate("searchScreen") } // Fixed clickable position
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
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = colorResource(R.color.teal_200))
            .padding(10.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text("Hey ", color = Color.White, fontSize = 32.sp)
            Text("Welcome back ", color = Color.White, fontSize = 22.sp)
            Text("Anjali ", color = Color.White, fontSize = 22.sp)
        }

        Spacer(modifier = Modifier.weight(1f)) // Push image to the right

        Image(
            painter = painterResource(id = R.drawable.img3),
            contentDescription = "App Logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.Gray)
                .padding(5.dp)
        )
    }
}

@Composable
fun Tools() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = colorResource(R.color.white))
            .padding(10.dp)
    ) {
        repeat(5) {
            Column(modifier = Modifier.padding(10.dp)) {
                Image(
                    painter = painterResource(R.drawable.img4),
                    contentDescription = null,
                    modifier = Modifier.width(40.dp)
                )
                Text("Tool")
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carousel() {
    val items = listOf("Slide 1", "Slide 2", "Slide 3", "Slide 4")
    val pagerState = rememberPagerState { items.size }

    Column {
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth()) { page ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = colorResource(R.color.teal_200))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = items[page], fontSize = 22.sp, color = Color.White)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            items.indices.forEach { index ->
                Box(
                    modifier = Modifier
                        .size(if (pagerState.currentPage == index) 12.dp else 8.dp)
                        .background(
                            color = if (pagerState.currentPage == index)
                                colorResource(R.color.teal_200)
                            else Color.Gray,
                            shape = RoundedCornerShape(50)
                        )
                        .padding(4.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}

@Composable
fun Cards() {
    LazyRow {
        items(3) {
            Card(modifier = Modifier.padding(15.dp)) {
                Column(modifier = Modifier.padding(15.dp)) {
                    Image(
                        painter = painterResource(R.drawable.img3),
                        contentDescription = null,
                        modifier = Modifier.width(70.dp)
                    )
                    Text("Tool")
                }
            }
        }
    }
}





@Composable
fun DrawerContent(onItemClick: (String) -> Unit) {
    val menuItems = mapOf(
        "Home" to "home",
        "Profile" to "profile",
        "Settings" to "settings",
        "Logout" to "logout"
    )
    val drawerBgColor = MaterialTheme.colorScheme.primaryContainer

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(drawerBgColor)
            .padding(16.dp)
    ) {
        Spacer(Modifier.height(20.dp))
        Text(
            text = "Menu",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )

        menuItems.forEach { (title, route) ->
            Text(
                text = title,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(route) }
                    .padding(12.dp)
            )
        }
    }
}

// Screens
@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Profile Screen", fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SettingsScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Settings Screen", fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun LogoutScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Logout Screen", fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}
