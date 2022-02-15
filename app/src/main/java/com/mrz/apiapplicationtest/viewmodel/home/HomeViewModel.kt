package com.mrz.apiapplicationtest.viewmodel.home

import com.mrz.apiapplicationtest.data.models.Note
import com.mrz.apiapplicationtest.viewmodel.core.BaseViewModel
import com.mrz.apiapplicationtest.viewmodel.core.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : BaseViewModel() {

    private val _note = MutableStateFlow<Event<Note>>(Event.loading())
    val note: StateFlow<Event<Note>> = _note

    fun getNote1() {
        requestWithMutableFlow(_note) {
            api.getNote()
        }
    }
}