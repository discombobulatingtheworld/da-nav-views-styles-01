package com.example.ucu2024_android_navigation_views_styles_leccion1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.navigation.NavigationHost
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val navController = rememberNavController()
                NavigationHost(navController, "Login")
            }
        }
    }
}