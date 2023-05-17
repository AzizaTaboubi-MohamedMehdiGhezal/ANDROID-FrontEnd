package com.example.savy

import com.example.savy.model.ScrappedProduct

interface OnListProdClick {
    fun onItemClick(scrappedProduct: ScrappedProduct)
}