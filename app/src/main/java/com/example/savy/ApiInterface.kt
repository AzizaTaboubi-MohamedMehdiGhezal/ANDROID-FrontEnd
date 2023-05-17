package com.example.savy

import com.example.savy.model.*
import com.example.savy.view.newProduct
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @POST("user/login")
    fun seConnecter(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @GET("Product")
    fun getNewProducts(): Call<List<ScrappedProduct>>

    companion object {

        var BASE_URL = "http://10.0.2.2:9092/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }

        fun getAllNewProducts(): ApiInterface {
            val api = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            //api.getNewProducts().en
            return api.create(ApiInterface::class.java)
        }
    }
}