package com.sameershelar.toodo

import androidx.compose.ui.window.ComposeUIViewController
import com.sameershelar.toodo.di.initKoin
import com.sameershelar.toodo.presentation.App
import platform.UIKit.UIViewController

@Suppress("FunctionName")
fun MainViewController(): UIViewController {
    return ComposeUIViewController(
        configure = { initKoin() }
    ) {
        App()
    }
}