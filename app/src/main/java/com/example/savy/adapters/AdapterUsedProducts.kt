package com.example.savy.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.savy.R
import com.example.savy.databinding.ProductViewBinding
import com.example.savy.model.Products
import com.example.savy.utils.Constant

class AdapterUsedProducts(context: Context,var dataList: List<Products>) :
    RecyclerView.Adapter<AdapterUsedProducts.ViewHolder>() {
    private lateinit var editBtn: ImageButton
    private lateinit var delBtn: ImageButton

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ProductViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productName: TextView = holder.itemView.findViewById(R.id.productName)
        val productPrice: TextView = holder.itemView.findViewById(R.id.productPrice)
        val data = dataList[position]
        val requestOptions = RequestOptions().placeholder(R.drawable.savy_logo)
        Glide.with(holder.itemView.context).load(Constant.image_URL + data.image).error(R.drawable.savy_logo).into(holder.itemView.findViewById<ImageView>(R.id.imageProduit))
        editBtn = holder.itemView.findViewById(R.id.editBtn)
        delBtn = holder.itemView.findViewById(R.id.delBtn)
        productName.text = data.nom
        productPrice.text = data.prix.toString()
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
    inner class ViewHolder(itemView: ProductViewBinding) : RecyclerView.ViewHolder(itemView.root) {

    }
    }