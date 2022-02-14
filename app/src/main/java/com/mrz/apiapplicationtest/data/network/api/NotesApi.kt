package com.mrz.apiapplicationtest.data.network.api

import android.telecom.Call
import com.mrz.apiapplicationtest.data.models.Note
import retrofit2.http.GET

interface NotesApi {

    @GET("note/1")
    suspend fun getNote() : Note
}