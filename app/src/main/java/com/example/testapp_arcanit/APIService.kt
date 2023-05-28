package com.example.testapp_arcanit


import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface APIService {
    @GET("/search/users")
    suspend fun getUser(@Query("q") login: String): Response<ResponseBody>
}