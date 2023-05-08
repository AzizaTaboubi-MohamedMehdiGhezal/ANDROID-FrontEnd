package com.example.savy.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.savy.R
import com.example.savy.adapters.AdapterUsedProducts
import com.example.savy.model.User
import com.example.savy.services.APIService
import com.example.savy.services.produitService
import com.example.savy.utils.Constant
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class products : AppCompatActivity() {
    private lateinit var produitRV: RecyclerView
    private lateinit var addBtn:ImageButton
    private lateinit var productAdapter : AdapterUsedProducts

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {super.onCreate(savedInstanceState)
        setContentView(R.layout.user_products)
        produitRV = findViewById(R.id.produitRV)

        val sharedPreferences =  this@products.getSharedPreferences(Constant.SHARED_PREF_SESSION, Context.MODE_PRIVATE)
        val userData = sharedPreferences.getString("USER_DATA", "")
        addBtn = findViewById(R.id.addBtn)

        print(userData)

        if (userData != null) {
            val user = Gson().fromJson(userData, User::class.java)
            APIService.ProductService.getAll(produitService.getByID(user._id))
                .enqueue(

                    object : Callback<produitService.ProduitResponse> {
                        override fun onResponse(

                            call: Call<produitService.ProduitResponse>,
                            response: Response<produitService.ProduitResponse>
                        ) {
                            print(response.toString())
                            if (response.code() == 200) {
                                val products = response.body()?.products
                                println(response.body().toString())
                                println(products.toString())
                                if(products != null) {
                                    productAdapter.dataList = products
                                    productAdapter.notifyDataSetChanged()
                                }else{

                                    println("status code is " + response.code())

                                }

                            } else {
                                println("status code is " + response.code())
                            }

                        }

                        override fun onFailure(
                            call: Call<produitService.ProduitResponse>,
                            t: Throwable
                        ) {
                            println("HTTP ERROR")
                            t.printStackTrace()
                        }

                    }
                )

            addBtn.setOnClickListener {
                val intent = Intent(this@products, add_productActivity::class.java)
                startActivity(intent)
                    finish()
            }
            /*editBtn.setOnClickListener {
                val intent = Intent(this@products, updateused_products::class.java)
                startActivity(intent)
                finish()
            }*/
        }
        produitRV.layoutManager = LinearLayoutManager(this@products)
        productAdapter = AdapterUsedProducts(this@products,listOf())
        produitRV.adapter = productAdapter
    }
}