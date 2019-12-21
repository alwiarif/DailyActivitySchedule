package com.example.dailyactivityschedule.Database

import com.example.dailyactivityschedule.Model.DailyModel

class DailyRepository(private val dailyDao: DailyDao) {
    val allBarang = dailyDao.loadDaily()

    suspend fun insert(dailyModel: DailyModel){
        dailyDao.insertDaily(dailyModel)
    }

    suspend fun delete(dailyModel: DailyModel){
        dailyDao.deleteDaily(dailyModel)
    }

    suspend fun update(dailyModel: DailyModel){
        dailyDao.updateDaily(dailyModel)
    }

}