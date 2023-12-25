package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.repository.UserRepository
import com.example.myapplication.retrofit.ApiConfig

object Injection {
    fun provideUserRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService(context)
        return UserRepository(apiService)
    }
}