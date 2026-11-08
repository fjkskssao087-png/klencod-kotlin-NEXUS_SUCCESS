package com.nexus.reverse.ui

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.util.zip.ZipInputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolScreen(toolId: String, navController: NavController) {
    val context = LocalContext.current
    var resultText by remember { mutableStateOf("جاهز للتحليل... اختر ملف APK من الأسفل.") }
    var isLoading by remember { mutableStateOf(false) }

    val filePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            isLoading = true
            Thread {
                try {
                    val inputStream = context.contentResolver.openInputStream(uri)
                    val zipStream = ZipInputStream(inputStream)
                    var entry = zipStream.nextEntry
                    var fileCount = 0
                    var dexCount = 0
                    var hasManifest = false

                    while (entry != null) {
                        fileCount++
                        if (entry.name.endsWith(".dex")) dexCount++
                        if (entry.name == "AndroidManifest.xml") hasManifest = true
                        entry = zipStream.nextEntry
                    }
                    zipStream.close()
                    
                    resultText = "✅ تم التحليل بنجاح\n\n" +
                            "📁 عدد الملفات: $fileCount\n" +
                            "📄 ملفات DEX: $dexCount\n" +
                            "📜 المانيفست: $hasManifest"
                } catch (e: Exception) {
                    resultText = "❌ خطأ: \${e.message}"
                }
                isLoading = false
            }.start()
        }
    }

    val toolName = toolsList.find { it.id == toolId }?.title ?: "TOOL"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(toolName, color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Text("⬅️", fontSize = 20.sp)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        }
    ) { padding ->
        Column(
            Modifier.padding(padding).fillMaxSize().background(Color(0xFF0A0A0F)).padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color(0xFF00E5FF))
                Spacer(Modifier.height(20.dp))
                Text("جاري التحليل...", color = Color.White)
            } else {
                Text(resultText, color = Color(0xFF00E5FF), fontFamily = FontFamily.Monospace, fontSize = 16.sp)
            }

            Spacer(Modifier.weight(1f))

            Button(
                onClick = { filePicker.launch("application/vnd.android.package-archive") },
                modifier = Modifier.fillMaxWidth().height(60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9D4EDD)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("اختر ملف APK", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}