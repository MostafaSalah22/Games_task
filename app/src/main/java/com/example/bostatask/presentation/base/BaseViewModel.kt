package com.example.bostatask.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE : ViewState, EVENT : ViewIntent>(
) : ViewModel() {

    private val initState by lazy { setInitViewState() }
    abstract fun setInitViewState(): STATE

    private val _viewState: MutableStateFlow<STATE> = MutableStateFlow(initState)
    val viewState: StateFlow<STATE> get() = _viewState

    private var _intents: MutableSharedFlow<EVENT> = MutableSharedFlow(extraBufferCapacity = 1)


    init {
        subscribeToEvents()
    }

    protected abstract fun reduce(intent: ViewIntent)

    private fun subscribeToEvents() {
        viewModelScope.launch {
            _intents.collect { intents ->
                reduce(intents)
            }
        }
    }

    fun setState(reducer: STATE.() -> STATE) {
        val newState = viewState.value.reducer()
        _viewState.value = newState
    }

    fun sendIntent(event: EVENT) {
        _intents.tryEmit(event)
    }


    fun navigationConsumed() {
        viewState.value.navigation = null
    }

}



