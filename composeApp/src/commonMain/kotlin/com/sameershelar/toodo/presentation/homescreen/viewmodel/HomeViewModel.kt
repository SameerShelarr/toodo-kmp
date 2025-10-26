package com.sameershelar.toodo.presentation.homescreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sameershelar.toodo.domain.models.Toodo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.sameershelar.toodo.domain.data.ToodoRepository as ToodoRepositoryInterface

class HomeViewModel(
    private val repo: ToodoRepositoryInterface,
) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        refreshToodos()
    }

    fun refreshToodos() {
        viewModelScope.launch {
            _isRefreshing.value = true
            delay(2000)
            repo.fetchAndSaveAllToodos()
            _isRefreshing.value = false
        }
    }

    fun getAllToodos() = repo.getAllToodos()

    fun addToodo(toodo: Toodo) {
        viewModelScope.launch {
            repo.addToodo(toodo)
        }
    }

    fun updateToodo(toodo: Toodo) {
        viewModelScope.launch {
            repo.updateToodo(toodo)
        }
    }

    fun deleteToodo(toodo: Toodo) {
        viewModelScope.launch {
            repo.deleteToodo(toodo)
        }
    }
}