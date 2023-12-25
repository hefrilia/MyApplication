package com.example.myapplication.retrofit

import com.example.myapplication.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int? = null,
        @Query("per_page") perPage: Int? = null
    ): UserResponse
}