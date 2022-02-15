package com.mrz.apiapplicationtest.viewmodel.notes

import com.mrz.apiapplicationtest.data.models.Note
import com.mrz.apiapplicationtest.viewmodel.core.BaseViewModel
import com.mrz.apiapplicationtest.viewmodel.core.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NotesViewModel: BaseViewModel() {

    private val _note = MutableStateFlow<Event<Note>>(Event.idle())
    val note: StateFlow<Event<Note>> = _note

    fun getNoteById(id: Int) {
        requestWithMutableFlow(_note) {
            api.getNoteById(id)
        }
    }
}