
package com.example.dailyactivityschedule.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyactivityschedule.Adapter.ListDailyAdapter
import com.example.dailyactivityschedule.R
import com.example.dailyactivityschedule.ViewModel.DailyViewModel
import com.example.dailyactivityschedule.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: ListDailyAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var viewModel: DailyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )

        viewModel = ViewModelProviders.of(this).get(DailyViewModel::class.java)

        viewManager = LinearLayoutManager(this)
        viewAdapter = ListDailyAdapter(viewModel)

        recyclerView = binding.recycleSchedule

        recyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter

        }

        viewModel.items.observe(this, Observer {
                list -> viewAdapter.submitList(list.toMutableList())
        })

        binding.fabAdd.setOnClickListener {
            startActivity(intentFor<DailyAddActivity>().clearTask().clearTop())
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

    }
}
