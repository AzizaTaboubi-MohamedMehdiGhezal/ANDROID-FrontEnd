package com.example.savy.services

import com.example.savy.model.LoginRequest
import com.example.savy.model.LoginResponse
import com.example.savy.utils.Constant
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.POST

object APIService {

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constant.BASE_URL)
            .build()
    }
    val UserService: userService by lazy {
        retrofit().create(userService::class.java)
    }
    val ProductService: produitService by lazy {
        retrofit().create(produitService::class.java)
    }

}