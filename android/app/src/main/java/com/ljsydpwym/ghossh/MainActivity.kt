package com.ljsydpwym.ghossh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ljsydpwym.ghossh.data.repository.SettingsRepository
import com.ljsydpwym.ghossh.ui.ApplicationNavController
import com.ljsydpwym.ghossh.ui.theme.ChuColors
import com.ljsydpwym.ghossh.ui.theme.ChuTheme
import com.ljsydpwym.ghossh.ui.theme.GhosttyThemeRegistry

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(0x00000000),
            navigationBarStyle = SystemBarStyle.dark(0x00000000),
        )
        setContent {
            AppRoot()
        }
    }
}

@Composable
fun AppRoot() {
    val context = LocalContext.current
    GhosttyThemeRegistry.init(context)
    val settings = SettingsRepository.getInstance(context)
    val themeName by settings.themeName.collectAsStateWithLifecycle()

    ChuTheme(themeName = themeName) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(ChuColors.current.background),
        ) {
            ApplicationNavController()
        }
    }
}
