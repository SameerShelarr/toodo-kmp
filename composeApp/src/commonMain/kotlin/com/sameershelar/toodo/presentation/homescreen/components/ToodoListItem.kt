package com.sameershelar.toodo.presentation.homescreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.sameershelar.toodo.domain.models.Toodo
import com.sameershelar.toodo.util.toColor
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import toodo.composeapp.generated.resources.Res
import toodo.composeapp.generated.resources.bin_icon

@Composable
@Preview(showBackground = true)
fun ToodoListItem(
    modifier: Modifier = Modifier,
    toodo: Toodo,
    onCheckChanged: (Toodo) -> Unit = {},
    onDelete: (Toodo) -> Unit = {}
) {
    @Suppress("DEPRECATION") val swipeToDismissBoxState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it == SwipeToDismissBoxValue.EndToStart || it == SwipeToDismissBoxValue.StartToEnd) {
                onDelete.invoke(toodo)
            }
            return@rememberSwipeToDismissBoxState false
        })

    SwipeToDismissBox(
        state = swipeToDismissBoxState, modifier = modifier.fillMaxSize(), backgroundContent = {
            when (swipeToDismissBoxState.dismissDirection) {
                SwipeToDismissBoxValue.StartToEnd, SwipeToDismissBoxValue.EndToStart -> {
                    Box(
                        Modifier.fillMaxSize().padding(4.dp).clip(
                            shape = RoundedCornerShape(16.dp)
                        ).background(
                            MaterialTheme.colorScheme.errorContainer
                        )
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.bin_icon),
                            contentDescription = "Remove toodo",
                            modifier = Modifier.size(46.dp).align(
                                if (swipeToDismissBoxState.dismissDirection == SwipeToDismissBoxValue.StartToEnd) {
                                    Alignment.CenterStart
                                } else {
                                    Alignment.CenterEnd
                                }
                            ).padding(
                                end = 12.dp,
                                start = 12.dp
                            ),
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }

                SwipeToDismissBoxValue.Settled -> {}
            }
        }) {
        Box(
            modifier = modifier.fillMaxWidth()
                .clip(shape = RoundedCornerShape(16.dp))
                .border(
                    width = 2.dp,
                    color = toodo.color.toColor(),
                    shape = RoundedCornerShape(16.dp)
                )
                .background(
                    MaterialTheme.colorScheme.background
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = toodo.isCompleted,
                    onCheckedChange = { checked ->
                        onCheckChanged.invoke(
                            toodo.copy(isCompleted = checked)
                        )
                    },
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Text(
                    text = toodo.title,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}
