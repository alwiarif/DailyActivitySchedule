package com.example.dailyactivityschedule.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class DailyModel (
    @PrimaryKey(autoGenerate = true) var Id: Int,
    var namahari: String,
    var activitas: String
)