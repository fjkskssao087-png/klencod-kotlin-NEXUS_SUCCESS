package com.nexus.reverse.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    var startAnim by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(if (startAnim) 1f else 0f, tween(1000), label = "a")
    
    LaunchedEffect(Unit) {
        startAnim = true
        delay(2000)
        onFinished()
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.alpha(alpha)) {
            Text("NEXUS", fontSize = 64.sp, fontWeight = FontWeight.Black, color = Color.White, letterSpacing = 4.sp)
            Text("REVERSE ENGINEERING", fontSize = 12.sp, color = Color(0xFF00E5FF), letterSpacing = 6.sp, fontWeight = FontWeight.Bold)
        }
    }
}