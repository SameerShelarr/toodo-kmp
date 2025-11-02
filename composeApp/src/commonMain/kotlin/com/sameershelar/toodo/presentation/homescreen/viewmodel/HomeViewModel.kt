package com.sameershelar.toodo.presentation.homescreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sameershelar.toodo.domain.models.Toodo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.sameershelar.toodo.domain.data.ToodoRepository as ToodoRepositoryInterface

class HomeViewModel(
    private val repo: ToodoRepositoryInterface,
    scope: CoroutineScope? = null,
) : ViewModel() {

    private val coroutineScope: CoroutineScope = scope ?: viewModelScope

    private val eventChannel = Channel<ToodoEvent>(Channel.BUFFERED)
    val event = eventChannel.receiveAsFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    val toodos = repo.getAllToodos()
        .catch {
            eventChannel.send(
                ToodoEvent.Error(
                    message = "Error while fetching data!"
                )
            )
        }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        refreshToodos()
    }

    fun refreshToodos() {
        coroutineScope.launch(Dispatchers.IO) {
            _isRefreshing.value = true
            try {
                repo.fetchAndSaveAllToodos()
            } catch (_: Exception) {
                ensureActive()
                eventChannel.send(
                    ToodoEvent.Error(
                        message = "Error while fetching and saving data!"
                    )
                )
            }
            _isRefreshing.value = false
        }
    }

    fun addToodo(toodo: Toodo) {
        coroutineScope.launch(Dispatchers.IO) {
            repo.addToodo(toodo)
        }
    }

    fun updateToodo(toodo: Toodo) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                repo.updateToodo(toodo)
            } catch (_: Exception) {
                ensureActive()
                eventChannel.send(
                    ToodoEvent.Error(
                        message = "Error updating toodo on server!"
                    )
                )
            }
        }
    }

    fun deleteToodo(toodo: Toodo) {
        coroutineScope.launch(Dispatchers.IO) {
            repo.softDeleteToodo(toodo)
            eventChannel.send(
                ToodoEvent.ToodoDeleted(
                    toodo = toodo
                )
            )
        }
    }

    fun restoreToodo(toodo: Toodo) {
        coroutineScope.launch(Dispatchers.IO) {
            repo.softRestoreToodo(toodo)
        }
    }
}

sealed class ToodoEvent {
    data class Error(val message: String) : ToodoEvent()
    data class ToodoDeleted(val toodo: Toodo) : ToodoEvent()
}