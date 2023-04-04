package com.example.savy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.savy.model.LoginRequest
import com.example.savy.model.LoginResponse
import com.example.savy.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel: UserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.login(LoginRequest("medmehdi.ghezal@gmail.com", "Test2020!")){
        }
    }
}