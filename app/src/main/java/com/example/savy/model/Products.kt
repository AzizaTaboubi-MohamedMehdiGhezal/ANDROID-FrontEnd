package com.example.savy.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Products (val _id: String,
                     val userID: String,
                     val nom: String,
                     val prix: Int,
                     val image: String,
                     val promo: Int,
                     val etat: String,
                     val marque: String,
                     val boutique: String,
                     val annee: Int,
                     val description: String,
                     val type: String,
                     val city: String
): Serializable, Parcelable