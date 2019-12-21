package com.example.dailyactivityschedule.Database

import androidx.lifecycle.LiveData
import com.example.dailyactivityschedule.Model.UserModel


class UserRepository private constructor(private val userDao: UserDao) {

    private val userAccountLiveData: LiveData<UserModel>? = null

     fun isValidAccount(username: String, password: String): Boolean {

        val userAccount = userDao.getAccount(username)
         if (userAccount == null){
             return false
         }else{
             return userAccount.password == password

         }

     }

     fun insertUser(username: String, password: String) {
        val account = UserModel(username, password)
        userDao.insert(account)
    }

    companion object{
        private var instace: UserRepository? = null

        fun getInstance(userDao: UserDao): UserRepository{
            if (instace == null){
                instace = UserRepository(userDao)
            }
            return instace!!
        }
    }

}