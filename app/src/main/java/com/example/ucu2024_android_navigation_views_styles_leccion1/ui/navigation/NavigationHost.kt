package com.example.ucu2024_android_navigation_views_styles_leccion1.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.example.ucu2024_android_navigation_views_styles_leccion1.model.dummy.generateEventDummyData
import com.example.ucu2024_android_navigation_views_styles_leccion1.model.dummy.generateProfileDummyData
import com.example.ucu2024_android_navigation_views_styles_leccion1.state.ActivityState
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.views.HomeView
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.views.LoginView
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.views.ProfilePublicView

@Composable
fun NavigationHost(
    navController: NavHostController,
    startDestination: String,
) {
    val activityState = ActivityState(
        remember { mutableStateOf(generateProfileDummyData()) },
        remember { mutableStateOf(generateEventDummyData()) },
    )
    NavHost(navController, startDestination) {
        composable("Login") {
            LoginView(
                activityState = activityState,
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
            backStackEntry.arguments?.getString("userMail")!!,
            activityState = activityState,
            onDetailsNavigation = { user: String ->
                navController.navigate("Profile/${user}/Public")
            },
            onLogoutNavigation = {
                navController.navigate("Login")
            }
        ) }
        composable(
            "Profile/{userMail}/Public",
            arguments = listOf(
                navArgument("userMail") { type = NavType.StringType }
            )
        ) { backStackEntry -> ProfilePublicView(
            userMail =  backStackEntry.arguments?.getString("userMail"),
            activityState = activityState,
            onBackNavigation = {
                navController.popBackStack()
            }
        )


        }
    }
}