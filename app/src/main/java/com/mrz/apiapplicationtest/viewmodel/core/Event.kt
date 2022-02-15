package com.mrz.apiapplicationtest.viewmodel.core

data class Event<out T>(val status: Status, val data: T?, val error: String?) {

    companion object {
        fun <T> loading(): Event<T> {
            return Event(Status.LOADING, null, null)
        }

        fun <T> success(data: T?): Event<T> {
            return Event(Status.SUCCESS, data, null)
        }

        fun <T> error(error: String?): Event<T> {
            return Event(Status.ERROR, null, error)
        }

        fun <T> idle(): Event<T> {
            return Event(Status.IDLE, null, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    IDLE
}