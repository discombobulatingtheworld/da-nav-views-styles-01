package com.example.ucu2024_android_navigation_views_styles_leccion1.ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.views.home.HomeView
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.views.login.LoginView
import kotlinx.coroutines.CoroutineScope

@Composable
fun NavigationHost(
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(navController, startDestination) {
        composable("Login") {
            LoginView(
                onLoginNavigation = { user: String ->
                    navController.navigate("Home/${user}")
                }
            )
        }
        composable(
            "Home/{userMail}",
            arguments = listOf(
                navArgument("userMail") { type = NavType.StringType}
            )
        ) { backStackEntry -> HomeView(
            navController,
            backStackEntry.arguments?.getString("userMail")
        ) }
    }
}