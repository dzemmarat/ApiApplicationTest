package com.mrz.apiapplicationtest.viewmodel.messages

import com.mrz.apiapplicationtest.data.models.Note
import com.mrz.apiapplicationtest.viewmodel.core.BaseViewModel
import com.mrz.apiapplicationtest.viewmodel.core.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MessagesViewModel: BaseViewModel() {

    private val _notes = MutableStateFlow<Event<List<Note>>>(Event.loading())
    val notes: StateFlow<Event<List<Note>>> = _notes

    fun getNotes() {
        requestWithMutableFlow(_notes) {
            api.getNotes()
        }
    }
}