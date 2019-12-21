package com.example.dailyactivityschedule.View

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.dailyactivityschedule.R
import com.example.dailyactivityschedule.ViewModel.UserViewModel
import com.example.dailyactivityschedule.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class UserActivity : AppCompatActivity() {


    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        viewModel = ViewModelProviders.of(this, UserViewModel.Factory(applicationContext)).get(UserViewModel::class.java)

        binding.loginActivityButton.setOnClickListener {
            val username = login_activity_username.text.toString()
            val password = login_activity_password.text.toString()
            if (username.isEmpty() || password.isEmpty()){
                toast("Masukkan kolom yang kosong")
                return@setOnClickListener
            }
            val isValid = viewModel!!.checkValidLogin(binding.loginActivityUsername!!.text.toString(),
                binding.loginActivityPassword!!.text.toString())
            if (isValid){
                toast("Login Berhasil")
                Log.i("sukses_login", "Login Berhasil")
                startActivity(intentFor<MainActivity>().clearTask().clearTop())
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_in_left)
                finish()
            }else{
                toast("Login gagal")
                Log.i("login gagal", "Login tidak berhasil")
            }
        }

        binding.loginActivityRegisterText.setOnClickListener {
            startActivity(intentFor<UserRegisActivity>().clearTask().clearTop())
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }



    }
}