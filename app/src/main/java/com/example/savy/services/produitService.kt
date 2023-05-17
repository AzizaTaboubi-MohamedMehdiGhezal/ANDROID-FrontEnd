package com.example.savy.services

import com.example.savy.model.Products
import retrofit2.Call
import retrofit2.http.*

interface produitService {

    data class ProductResponse(
        val produit:Products
    )
    data class ProduitResponse(
        val products: List<Products>
    )

    data class addProduct(
        val userID: String,
        val nom: String,
        val prix: Int,
        val image: String,
        val promo: Int,
        val etat: String,
        val marque: String,
        val annee: Int,
        val description: String,
        val type: String,
        val city: String,
    )
    data class updateProduct(
        val nom: String,
        val prix: Int,
        val image: String,
        val promo: Int,
        val etat: String,
        val marque: String,
        val annee: Int,
        val description: String,
        val type: String,
        val city: String,
    )

data class getByID(
    val userID: String
)
    //
    @POST("/produit/getByUserID")
    fun getAll(@Body userID: getByID): Call<ProduitResponse>
    @GET("/produit/searchUserProd")
    fun getSearch(): Call<ProduitResponse>
    @GET("/produit/produits")
    fun getAll(): Call<ProduitResponse>
    @POST("/produit/addNewProduct")
    fun addProduct(@Body newProd: addProduct): Call<ProductResponse>
    @POST("/produit/update_prod")
    fun update(updateProduct: updateProduct): Call<updateProduct>
    //change method Delete
    @POST("/produit/delete_prod")
    fun delete()

}