package com.sameershelar.toodo.presentation.homescreen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.LoadingIndicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sameershelar.toodo.di.composePreviewModule
import com.sameershelar.toodo.di.sharedModule
import com.sameershelar.toodo.presentation.homescreen.viewmodel.HomeViewModel
import com.sameershelar.toodo.presentation.homescreen.viewmodel.ToodoEvent
import com.sameershelar.toodo.ui.theme.ToodoAppTheme
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinInject(),
) {
    val toodos by viewModel.toodos.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.event.collectLatest { event ->
            when (event) {
                is ToodoEvent.Error -> {
                    snackbarHostState.showSnackbar(message = event.message)
                }

                is ToodoEvent.ToodoDeleted -> {
                    val result = snackbarHostState.showSnackbar(
                        message = "Toodo deleted",
                        actionLabel = "Undo",
                        duration = SnackbarDuration.Short
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.restoreToodo(toodo = event.toodo)
                    }
                }
            }
        }
    }

    ToodoAppTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState
                )
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                AppBar(
                    modifier = Modifier
                        .statusBarsPadding()
                        .fillMaxWidth()
                        .padding(
                            start = 20.dp,
                            end = 20.dp,
                            top = 20.dp,
                            bottom = 10.dp,
                        )
                )

                val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle(false)

                AnimatedVisibility(
                    visible = isRefreshing
                ) {
                    LinearWavyProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 16.dp
                            )
                    )
                }

                val pullToRefreshState = rememberPullToRefreshState()

                PullToRefreshBox(
                    isRefreshing = false,
                    onRefresh = {
                        viewModel.refreshToodos()
                    },
                    state = pullToRefreshState,
                    modifier = Modifier.fillMaxSize(),
                    indicator = {
                        LoadingIndicator(
                            modifier = Modifier.align(Alignment.TopCenter),
                            isRefreshing = isRefreshing,
                            state = pullToRefreshState,
                        )
                    }
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                            )
                    ) {
                        items(
                            items = toodos,
                            key = { it.id },
                        ) { toodo ->
                            ToodoListItem(
                                modifier = Modifier
                                    .padding(
                                        vertical = 4.dp,
                                    ).animateItem(),
                                toodo = toodo,
                                onCheckChanged = { toodo ->
                                    viewModel.updateToodo(
                                        toodo = toodo
                                    )
                                },
                                onDelete = { toodo ->
                                    viewModel.deleteToodo(
                                        toodo = toodo
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    KoinApplication(application = {
        modules(sharedModule, composePreviewModule)
    }) {
        HomeScreen()
    }
}