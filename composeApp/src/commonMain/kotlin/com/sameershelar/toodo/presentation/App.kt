package com.sameershelar.toodo.presentation

import androidx.compose.runtime.Composable
import com.sameershelar.toodo.presentation.homescreen.components.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview(
    showBackground = true,
)
fun App() {
    HomeScreen()
}