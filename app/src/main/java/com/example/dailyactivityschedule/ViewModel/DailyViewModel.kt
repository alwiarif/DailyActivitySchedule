package com.example.dailyactivityschedule.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.dailyactivityschedule.Database.DailyDao
import com.example.dailyactivityschedule.Database.DailyDatabase
import com.example.dailyactivityschedule.Database.DailyRepository
import com.example.dailyactivityschedule.Model.DailyModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DailyViewModel(application: Application) : AndroidViewModel(application) {

    //add Repo
    private val repository : DailyRepository
    private val dailyDao : DailyDao

    private val _items : LiveData<List<DailyModel>>

    val items : LiveData<List<DailyModel>>
    get() = _items

    //coroutine
    private var vmJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.IO + vmJob)

    init {
        dailyDao = DailyDatabase.getInstance(application).dailyDao()
        repository = DailyRepository(dailyDao)
        _items = repository.allBarang
    }

    fun addItem(nama: String, content: String) {
        uiScope.launch {
            repository.insert(
                DailyModel(0, nama, content)
            )
        }
    }

    fun removeItem(dailyModel: DailyModel){
        uiScope.launch {
            repository.delete(dailyModel)
        }
    }

    fun updateItem(dailyModel: DailyModel){
        uiScope.launch {
            repository.update(dailyModel)
        }
    }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }

}