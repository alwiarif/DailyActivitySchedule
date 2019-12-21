package com.example.dailyactivityschedule.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dailyactivityschedule.Model.UserModel


@Dao
interface UserDao{
    @Query("SELECT * FROM usermodel WHERE usermodel.userId LIKE :username")
    fun getAccount(username: String): UserModel

    @Insert
    fun insert(account: UserModel)


}