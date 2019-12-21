package com.example.dailyactivityschedule.Model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class UserModel(
    @PrimaryKey
    var userId: String,
    var password: String
)