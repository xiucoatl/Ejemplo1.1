package com.example.ejemplo11

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiServiceInterface {
    @GET
    suspend fun getResponse(@Url url: String): Response<Respuesta>
}