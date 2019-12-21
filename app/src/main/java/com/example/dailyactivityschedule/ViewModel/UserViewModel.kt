package com.example.dailyactivityschedule.ViewModel

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dailyactivityschedule.Database.UserDatabase
import com.example.dailyactivityschedule.Database.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class UserViewModel(context: Context) : ViewModel() {

    private val userRepository : UserRepository

    init {
        userRepository = UserRepository.getInstance(UserDatabase.getAppDatabase(context).userDao())
    }

    internal fun createUser(username: String, password:String){
        userRepository.insertUser(username, password)
    }

    internal fun checkValidLogin(username: String, password: String): Boolean{
            return userRepository.isValidAccount(username, password)

    }

    class Factory internal constructor(ctxt: Context): ViewModelProvider.Factory{
        private val ctxt: Context

        init {
            this.ctxt = ctxt.applicationContext
        }

        override fun <T : ViewModel> create(modelClass: Class<T>): T{
            return UserViewModel(ctxt) as T
        }
    }

}