package com.example.questtugasuserinput_0178

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.questtugasuserinput_0178.ui.screen.FormBiodataScreen
import com.example.questtugasuserinput_0178.ui.theme.QuestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { QuestTheme { FormBiodataScreen() } }
    }
}