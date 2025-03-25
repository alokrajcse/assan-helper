package com.example.helper

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.helper.screens.DrawerContent
import com.example.helper.screens.HomeScreen
import com.example.helper.screens.LogoutScreen
import com.example.helper.screens.ProfileScreen
import com.example.helper.screens.SearchScreen
import com.example.helper.screens.SettingsScreen
import com.example.helper.ui.theme.HelperTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            HelperTheme {
                val navController: NavHostController = rememberNavController()
                SidebarDemo(navController)
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SidebarDemo(navController: NavHostController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent { selectedItem ->
                navController.navigate(selectedItem) // Navigate using NavController
                coroutineScope.launch { drawerState.close() }
            }
        }
    ) {
        Scaffold(

        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                NavHost(navController, startDestination = "home") {
                    composable("home") { HomeScreen(navController=navController) }
                    composable(
                        "searchScreen",
                        enterTransition = {
                            scaleIn(
                                initialScale = 0.8f, // Start from 80% size
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = EaseIn
                                )
                            ) + fadeIn(
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = LinearEasing
                                )
                            )
                        },
                        exitTransition = {
                            scaleOut(
                                targetScale = 1.2f, // Exit by zooming out to 120% size
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = EaseOut
                                )
                            ) + fadeOut(
                                animationSpec = tween(
                                    durationMillis = 300,
                                    easing = LinearEasing
                                )
                            )
                        }
                    )
                    { SearchScreen(navController = navController) }
                    composable("profile") { ProfileScreen() }
                    composable("settings") { SettingsScreen() }
                    composable("logout") { LogoutScreen() }
                }
            }
        }
    }
}
