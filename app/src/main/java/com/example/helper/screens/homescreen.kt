package com.example.helper.screens

import android.R.attr.onClick
import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.ui.text.style.TextAlign
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
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var feedbacktext by remember{ mutableStateOf("")}

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

                Text(
                    text = "Feedback",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(value = feedbacktext, onValueChange = {feedbacktext=it},
                    label = {Text("Write Your Feedback")})
                // Sheet content
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                        }
                    }
                }) {
                    Text("Submit Feedback")
                }
                Spacer(modifier = Modifier.height(38.dp))
            }
        }
    }

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
                        IconButton(onClick = { navController.navigate("notificationscreen") }) {
                            Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                        }
                    }
                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize()) {



                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                        .padding(paddingValues)
                ) {
                    item {
                        Header(navController)
                        Spacer(modifier = Modifier.height(16.dp))
                        SearchBar(navController, paddingValues)
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp) // Ensures spacing is uniform
                        ) {
                            UploadYourNotes(modifier = Modifier.weight(1f))
                            RequestNotes(modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Carousel()
                        Spacer(modifier = Modifier.height(16.dp))
                        Tools()
                        Spacer(modifier = Modifier.height(16.dp))
                        Carousel()
                        Spacer(modifier = Modifier.height(16.dp))
                        Cards(
                            onFeedbackClick = {
                                showBottomSheet = true


                            },
                            onContactUsClick = {}
                        )
                        DonateCard(onDonateClick = {})
                        Spacer(modifier = Modifier.height(90.dp))
                    }
                }
//                Row (modifier = Modifier.fillMaxWidth().align(Alignment.BottomEnd), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
//
//                    Column {
//                ExtendedFloatingActionButton(
//
//
//
//                    onClick = { /* TODO: Handle FAB Click */ },
//                    modifier = Modifier
//
//                        .padding(16.dp),
//                    containerColor = MaterialTheme.colorScheme.primary
//                ) {
//                    Row {  Icon(Icons.Filled.Add, contentDescription = "Upload Notes")
//                    Text("Upload Your Notes")
//                    }
//                }
//
//                        Spacer(modifier= Modifier.height(20.dp))
//                    }
//
//
//                }




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
fun UploadYourNotes(modifier: Modifier = Modifier) {
    ExtendedFloatingActionButton(
        onClick = { /* Handle Upload */ },
        modifier = modifier.fillMaxWidth(), // Ensures equal width
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), // Makes inner Row stretch fully
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Upload Notes")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Upload Notes")
        }
    }
}

@Composable
fun RequestNotes(modifier: Modifier = Modifier) {
    ExtendedFloatingActionButton(
        onClick = { /* Handle Request */ },
        modifier = modifier.fillMaxWidth(), // Ensures equal width
        containerColor = MaterialTheme.colorScheme.secondary
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.Email, contentDescription = "Request Notes")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Request Notes")
        }
    }
}
@Composable
fun Header(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = colorResource(R.color.teal_200))
            .padding(10.dp)
            .clickable(onClick = { navController.navigate("profile") })
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
        Column(modifier = Modifier
            .padding(5.dp)
            .weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.notes),
                contentDescription = null,
                modifier = Modifier.width(40.dp)
            )
            Text(text = "Class\nNotes",textAlign= TextAlign.Center, fontSize = 12.sp, style = TextStyle(fontWeight = FontWeight.Bold))
        }
        Column(modifier = Modifier
            .padding(5.dp)
            .weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.calculator),
                contentDescription = null,
                modifier = Modifier.width(40.dp)
            )
            Text(text = "CGPA\nCalculator",textAlign= TextAlign.Center, fontSize = 12.sp, style = TextStyle(fontWeight = FontWeight.Bold))
        }
        Column(modifier = Modifier
            .padding(5.dp)
            .weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {

                Image(
                    painter = painterResource(R.drawable.attendance),
                    contentDescription = null,
                    modifier = Modifier.width(40.dp)
                )


            Text(text = "Attendance\nCalculator",textAlign= TextAlign.Center, fontSize = 12.sp, style = TextStyle(fontWeight = FontWeight.Bold))
        }
        Column(modifier = Modifier
            .padding(5.dp)
            .weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                painter = painterResource(R.drawable.barcodescanner),
                contentDescription = null,
                modifier = Modifier.width(40.dp)
            )


            Text(text = "Generate\nBarcode",textAlign= TextAlign.Center, fontSize = 12.sp, style = TextStyle(fontWeight = FontWeight.Bold))
        }
//        repeat(4) {
//            Column(modifier = Modifier.padding(10.dp)) {
//                Image(
//                    painter = painterResource(R.drawable.img4),
//                    contentDescription = null,
//                    modifier = Modifier.width(40.dp)
//                )
//                Text("Tool")
//            }
//        }
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

data class CardItem(
    val title: String,
    val imageId: Int,
    val onClick: () -> Unit
)

@Composable
fun Cards(onFeedbackClick: () -> Unit, onContactUsClick: () -> Unit) {
    val cardItems = listOf(
        CardItem("About", R.drawable.aboutassan) { /* Handle About click */ },
        CardItem("Feedback", R.drawable.feedback) { onFeedbackClick() }, // Replace with your feedback image
        CardItem("Contact Us", R.drawable.contactus) { onContactUsClick() }  // Replace with your contact us image
    )

    LazyRow {
        items(cardItems) { cardItem ->
            Card(
                modifier = Modifier
                    .padding(15.dp)
                    .clickable { cardItem.onClick() }
            ) {
                Column(
                    modifier = Modifier.padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(cardItem.imageId),
                        contentDescription = cardItem.title,
                        modifier = Modifier.width(70.dp)
                    )
                    Text(cardItem.title, textAlign = TextAlign.Center)
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
//@Composable
//fun ProfileScreen() {
//    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//        Text(text = "Profile Screen", fontSize = 24.sp, fontWeight = FontWeight.Bold)
//    }
//}

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

@Composable
fun DonateCard(onDonateClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Support Us by Donating \uD83E\uDD7A ",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Your support helps us grow and improve!❤️",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onDonateClick,
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.teal_200)) // Green Color
            ) {
                Text(text = "Donate", color = Color.White)
            }
        }
    }
}
