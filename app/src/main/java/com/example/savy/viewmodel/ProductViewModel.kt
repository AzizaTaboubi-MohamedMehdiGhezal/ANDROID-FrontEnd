package com.example.savy.viewmodel

import androidx.lifecycle.ViewModel
import com.example.savy.ApiInterface
import com.example.savy.model.ScrappedProduct
import retrofit2.Call
import retrofit2.Response

class ProductViewModel: ViewModel() {
    fun scrapData() {
        ApiInterface.create().getNewProducts().enqueue(
            object : retrofit2.Callback<List<ScrappedProduct>> {
                override fun onResponse(
                    call: Call<List<ScrappedProduct>>,
                    response: Response<List<ScrappedProduct>>
                ) {
                    response.body()?.let {
                        println()

                    }
                }

                override fun onFailure(call: Call<List<ScrappedProduct>>, t: Throwable) {

                }

            })
    }
}

