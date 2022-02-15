package com.mrz.apiapplicationtest.data.network.api

import com.mrz.apiapplicationtest.data.models.Note
import retrofit2.http.GET
import retrofit2.http.Path

interface NotesApi {

    @GET("note/1")
    suspend fun getNote() : Note

    @GET("note/{id}")
    suspend fun getNoteById(
        @Path("id") id: Int
    ) : Note

    @GET("note")
    suspend fun getNotes() : List<Note>
}