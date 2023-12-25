package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.myapplication.paging.UserPaging
import com.example.myapplication.response.UserItem
import com.example.myapplication.retrofit.ApiService

class UserRepository(private val apiService: ApiService) {
    fun getUser(): LiveData <PagingData<UserItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                UserPaging(apiService)
            }
        ).liveData
    }
}