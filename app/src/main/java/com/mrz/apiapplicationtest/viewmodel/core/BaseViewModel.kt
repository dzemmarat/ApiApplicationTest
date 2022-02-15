package com.mrz.apiapplicationtest.viewmodel.core

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrz.apiapplicationtest.data.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

open class BaseViewModel : ViewModel() {
    var api = ApiClient.retrofitService()

    fun <T> requestWithMutableFlow(
        flow: MutableStateFlow<Event<T>>,
        request: suspend () -> T
    ) {
        flow.value = Event.loading()

        this.viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = request.invoke()
                flow.value = Event.success(response)
            } catch (e: Exception) {
                e.message?.let { Log.e("Error", it) }
                flow.value = Event.error(e.message)
            }
        }
    }
}