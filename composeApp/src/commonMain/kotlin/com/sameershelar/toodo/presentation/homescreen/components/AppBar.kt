package com.sameershelar.toodo.presentation.homescreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun AppBar(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = "Toodo",
            style = MaterialTheme.typography.titleLarge,
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}