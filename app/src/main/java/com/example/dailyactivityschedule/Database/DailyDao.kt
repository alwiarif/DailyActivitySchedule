package com.example.dailyactivityschedule.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dailyactivityschedule.Model.DailyModel

@Dao
interface DailyDao{
    @Query ("SELECT * FROM dailymodel")
    fun loadDaily() : LiveData<List<DailyModel>>

    @Insert
    fun insertDaily(dailyModel: DailyModel)

    @Update
    fun updateDaily(dailyModel: DailyModel)

    @Delete
    fun deleteDaily(dailyModel: DailyModel)
}
