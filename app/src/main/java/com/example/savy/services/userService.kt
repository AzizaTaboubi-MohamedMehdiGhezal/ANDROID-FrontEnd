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
        val newPassword: String,
        val newPasswordConfirm: String
    )
    data class updateProfileBody(
        val driverLicense: String,
        val name: String,
        val lastName: String,
        val token: String
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

    @PUT("/user/resetPassword")
    fun changePwd(@Body changePwdBody: changePwdBody): Call<UserResponse>

    @PUT("/user/updateUser")
    fun updateProfile(@Body updateProfileBody: updateProfileBody) : Call<UserResponse>
}