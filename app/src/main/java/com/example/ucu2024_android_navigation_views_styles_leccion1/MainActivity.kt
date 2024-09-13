package com.example.ucu2024_android_navigation_views_styles_leccion1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.navigation.NavigationHost
import com.example.ucu2024_android_navigation_views_styles_leccion1.ui.theme.alt1.Ucu2024androidnavigationviewsstylesleccion1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ucu2024androidnavigationviewsstylesleccion1Theme {
                val navController = rememberNavController()
                val scope = rememberCoroutineScope()
                val snackbarHostState = remember { SnackbarHostState() }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    }
                ) { innerPadding ->
                    NavigationHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = "Login",
                        scope = scope,
                        snackbarHostState = snackbarHostState,
                    )
                }
            }
        }
    }
}