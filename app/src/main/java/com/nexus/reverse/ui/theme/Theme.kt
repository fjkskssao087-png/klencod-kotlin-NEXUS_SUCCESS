package com.nexus.reverse.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val NexusColors = darkColorScheme(
    primary = Color(0xFF00E5FF), secondary = Color(0xFF9D4EDD),
    background = Color(0xFF000000), surface = Color(0xFF0A0A0F),
    onPrimary = Color.Black, onBackground = Color(0xFFF5F5F7)
)

@Composable
fun NexusTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = NexusColors, content = content)
}