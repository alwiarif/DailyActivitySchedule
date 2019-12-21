package com.example.dailyactivityschedule.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.dailyactivityschedule.R
import com.example.dailyactivityschedule.ViewModel.UserViewModel
import com.example.dailyactivityschedule.databinding.ActivityLoginBinding
import com.example.dailyactivityschedule.databinding.ActivityRegisterBinding
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast


class UserRegisActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val binding: ActivityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        viewModel = ViewModelProviders.of(this, UserViewModel.Factory(applicationContext)).get(UserViewModel::class.java)

        binding.registrationActivityButton.setOnClickListener {
            val username = registration_activity_username.text.toString()
            val password = registration_activity_password.text.toString()
            val cPasword = registration_activity_confirm_password.text.toString()
            if (username.isEmpty() || password.isEmpty() || cPasword.isEmpty()){
                toast("Masukkan kolom yang kosong")
                return@setOnClickListener
            }
            if(cPasword.equals(password)){
                viewModel!!.createUser(registration_activity_username!!.text.toString(),
                    registration_activity_password!!.text.toString())
                toast("Berhasil mendaftar")
                startActivity(intentFor<UserActivity>().clearTask().clearTop())
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
            else{
                toast("Confirm password tidak sesuai dengan password")
                return@setOnClickListener
            }
        }

        binding.registrationActivityLoginText.setOnClickListener {
            startActivity(intentFor<UserActivity>().clearTask().clearTop())
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

    }
}