package com.example.savy.services

import com.example.savy.model.User
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface userService {
    data class UserResponse(
        @SerializedName("user")
        val user: User
    )
    data class UserBody(
        val fullname: String,
        val email: String,
        val password: String,
        val numTel: String
    )
    data class LoginBody(
        val email: String,
        val password: String
    )
    data class emailBody(
        val email: String
    )
    data class otp(
        val otp: String
    )
    data class changePwdBody(
        val newPass: String,
        val email: String
    )
    data class updateProfileBody(
        val email: String,
        val fullname: String,
        val numTel: String
        )
    @POST("/user/register")
    fun signUp(@Body userBody: UserBody): Call<UserResponse>

    @POST("/user/send-confirmation-email")
    fun sendVerification(@Body emailBody: emailBody) : Call<UserResponse>

    @POST("/user/login")
    fun signIn(@Body loginBody: LoginBody): Call<UserResponse>

    @POST("/user/forgotPassword")
    fun sendOtp(@Body emailBody: emailBody): Call<UserResponse>

    @POST("/user/confirmationOtp")
    fun confirmOtp(@Body otp: otp): Call<UserResponse>

    @POST("/user/resetPassword")
    fun changePwd(@Body changePwdBody: changePwdBody): Call<UserResponse>

    @POST("/user/updateProfile")
    fun updateProfile(@Body updateProfileBody: updateProfileBody) : Call<UserResponse>
}