package com.nexus.reverse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nexus.reverse.ui.SplashScreen
import com.nexus.reverse.ui.DashboardScreen
import com.nexus.reverse.ui.ToolScreen
import com.nexus.reverse.ui.theme.NexusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NexusTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash") {
                    composable("splash") { SplashScreen { navController.navigate("dashboard") { popUpTo("splash") { inclusive = true } } } }
                    composable("dashboard") { DashboardScreen(navController) }
                    composable("tool/{toolId}") { backStackEntry ->
                        val toolId = backStackEntry.arguments?.getString("toolId") ?: ""
                        ToolScreen(toolId, navController)
                    }
                }
            }
        }
    }
}