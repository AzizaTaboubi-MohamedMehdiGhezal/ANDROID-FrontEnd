package com.example.savy.services

import com.example.savy.model.Product
import com.example.savy.model.User
import com.google.gson.annotations.SerializedName

interface produitService {
    data class produitResponse(
        @SerializedName("product")
        val product: Product
    )
}