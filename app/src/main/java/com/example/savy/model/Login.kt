package com.example.savy.model

data class LoginResponse(
    val token:String,
    val user: User
)

data class LoginRequest(
    val email:String,
    val password:String
)