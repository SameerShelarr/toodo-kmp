package com.sameershelar.toodo

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sameershelar.toodo.presentation.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Toodo",
    ) {
        App()
    }
}