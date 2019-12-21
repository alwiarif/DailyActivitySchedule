package com.example.dailyactivityschedule.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.dailyactivityschedule.R
import com.example.dailyactivityschedule.ViewModel.DailyViewModel
import kotlinx.android.synthetic.main.activity_add_daily.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class DailyAddActivity : AppCompatActivity() {

    private lateinit var viewModel: DailyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_daily)

        viewModel = ViewModelProviders.of(this).get(DailyViewModel::class.java)

        buttonUpdate.setOnClickListener {
            val edtHari = editDailyTitle.text.toString()
            val edtContent = editDailyContent.text.toString()
            if (edtHari.isEmpty() || edtContent.isEmpty()) {
                toast("Masukkan kolom yang kosong")
                return@setOnClickListener
            }else{
                viewModel.addItem(editDailyTitle.text.toString(), editDailyContent.text.toString())
                editDailyTitle.text = null
                editDailyContent.text = null
                startActivity(intentFor<MainActivity>().clearTask().clearTop())
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }

        }

        buttonCancel.setOnClickListener {
            startActivity(intentFor<MainActivity>().clearTask().clearTop())
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
