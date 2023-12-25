package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.repository.UserRepository
import com.example.myapplication.response.UserItem

class ThirdScreenViewModel (private val userRepository: UserRepository) : ViewModel() {
    companion object{
        private const val TAG = "UserViewModel"
    }

    val getPaging : LiveData<PagingData<UserItem>> = userRepository.getUser().cachedIn(viewModelScope)
}