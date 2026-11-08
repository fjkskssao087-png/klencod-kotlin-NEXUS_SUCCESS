package com.nexus.reverse.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class Tool(val id: String, val title: String, val icon: String, val color: Color)

val toolsList = listOf(
    Tool("apk", "APK ANALYZER", "📦", Color(0xFF00E5FF)),
    Tool("dex", "DEX EXTRACTOR", "📂", Color(0xFF9D4EDD)),
    Tool("class", "CLASS EXPLORER", "🧬", Color(0xFFFF006E)),
    Tool("method", "METHOD ANALYZER", "🔍", Color(0xFFFFB300)),
    Tool("smali", "SMALI VIEWER", "📝", Color(0xFF00E5FF)),
    Tool("pay", "PAYMENT ANALYZER", "💳", Color(0xFF9D4EDD))
)

@Composable
fun DashboardScreen(navController: NavController) {
    Column(Modifier.fillMaxSize().background(Color.Black)) {
        Column(Modifier.padding(24.dp, top = 60.dp, bottom = 20.dp)) {
            Text("NEXUS LAB", fontSize = 32.sp, fontWeight = FontWeight.Black, color = Color.White)
            Text("Advanced Reverse Engineering Suite", fontSize = 14.sp, color = Color.Gray)
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(20.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(toolsList) { tool ->
                Box(
                    modifier = Modifier.fillMaxWidth().height(130.dp)
                        .clip(RoundedCornerShape(20.dp)).background(Color(0xFF111111))
                        .clickable { navController.navigate("tool/\${tool.id}") }.padding(16.dp)
                ) {
                    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
                        Text(tool.icon, fontSize = 32.sp)
                        Text(tool.title, color = tool.color, fontSize = 13.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                    }
                }
            }
        }
    }
}