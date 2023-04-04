package com.example.savy.viewmodel

import androidx.lifecycle.ViewModel
import com.example.savy.ApiInterface
import com.example.savy.model.LoginRequest
import com.example.savy.model.LoginResponse
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class UserViewModel: ViewModel() {
    fun login(loginRequest: LoginRequest,callback: (LoginResponse)->Unit) {
        ApiInterface.create().seConnecter(loginRequest).enqueue(
            object : retrofit2.Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    response.body()?.let { callback(it) }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                }

            })
    }

}