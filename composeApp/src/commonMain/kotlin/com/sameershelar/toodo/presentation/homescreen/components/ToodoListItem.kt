package com.sameershelar.toodo.presentation.homescreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview(showBackground = true)
fun ToodoListItem(
    modifier: Modifier = Modifier,
    text: String = "Welcome to Toodo!",
    onCheckChanged: (Boolean) -> Unit = {},
) {
    Box(
        modifier = modifier.fillMaxWidth()
            .clip(
                shape = RoundedCornerShape(16.dp)
            )
            .background(Color.Cyan.copy(alpha = 0.2f))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            var isChecked by remember { mutableStateOf(false) }

            Checkbox(
                checked = isChecked,
                onCheckedChange = { checked ->
                    isChecked = !isChecked
                    onCheckChanged.invoke(isChecked)
                }
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}