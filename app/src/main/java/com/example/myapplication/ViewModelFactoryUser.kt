package com.example.myapplication

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.di.Injection
import com.example.myapplication.repository.UserRepository

class ViewModelFactoryUser private  constructor(private val historyRepository: UserRepository): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(ThirdScreenViewModel::class.java) -> {
                ThirdScreenViewModel(historyRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    companion object{
        @Volatile
        private var instance: ViewModelFactoryUser? = null

        fun getInstance(context: Context) : ViewModelFactoryUser =
            instance ?: synchronized(this){
                instance ?: ViewModelFactoryUser(
                    Injection.provideUserRepository(context),
                )
            }
    }
}