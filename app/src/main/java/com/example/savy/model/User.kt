package com.example.savy.model

import java.io.Serializable

data class User (
    val _id: String,
    val fullname: String,
    val profilepic: String,
    val email: String,
    val password: String,
    val numTel: String
): Serializable