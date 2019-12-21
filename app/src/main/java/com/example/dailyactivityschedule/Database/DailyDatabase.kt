package com.example.dailyactivityschedule.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dailyactivityschedule.Model.DailyModel

@Database(version = 1, entities = [DailyModel::class])
abstract class DailyDatabase : RoomDatabase() {

    abstract fun dailyDao() : DailyDao

    companion object{

        @Volatile
        private var INSTANCE: DailyDatabase? = null

        fun getInstance(context: Context) : DailyDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DailyDatabase::class.java,
                        "daily_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }

}