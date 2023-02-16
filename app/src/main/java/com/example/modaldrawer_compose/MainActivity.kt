package com.example.modaldrawer_compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.modaldrawer_compose.ui.theme.ModalDrawer_ComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val title = remember { mutableStateOf("Home") }
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            val navigationController = rememberNavController()

            ModalDrawer_ComposeTheme {
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        MyTopAppBar(title = title.value) {
                            scope.launch { scaffoldState.drawerState.open() }
                        }
                    },
                    drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                    drawerContent = {
                        DrawerHeader()
                        DrawerBody(
                            items = listOf(
                                MenuItem(id = "home", title = "home", contentDescription = "go to screen home", icon = Icons.Default.Home),
                                MenuItem(id = "user", title = "user", contentDescription = "go to screen user", icon = Icons.Default.Person),
                                MenuItem(id = "favorite", title = "favorite", contentDescription = "go to screen favorite", icon = Icons.Default.Favorite)
                            ),
                            onItemClick = {
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                    when(it.id) {
                                        "home" -> {
                                            title.value = "Home"
                                            navigationController.navigate(Routes.PantallaHome.route)
                                        }
                                        "user" -> {
                                            title.value = "user"
                                            navigationController.navigate(Routes.PantallaUSer.route)
                                        }
                                        "favorite" -> {
                                            title.value = "Favorite"
                                            navigationController.navigate(Routes.PantallaFavorite.route)
                                        }
                                    }
                                }
                            }
                        )
                    }
                ) {
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.PantallaHome.route
                    ) {
                        composable(Routes.PantallaHome.route) { PantallaHome() }
                        composable(Routes.PantallaUSer.route) { PantallaUser() }
                        composable(Routes.PantallaFavorite.route) { PantallaFavorite() }
                    }
                }
            }
        }
    }
}
