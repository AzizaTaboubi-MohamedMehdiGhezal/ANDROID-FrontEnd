package com.example.savy.view

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.savy.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class add_productActivity : AppCompatActivity() {
    private lateinit var productpic: ImageView
    private lateinit var txtName: TextInputEditText
    private lateinit var txtLayoutName: TextInputLayout
    private lateinit var txtPrice: TextInputEditText
    private lateinit var txtLayoutPrice: TextInputLayout
    private lateinit var txtDiscount: TextInputEditText
    private lateinit var txtLayoutDiscount: TextInputLayout
    private lateinit var txtYear: TextInputEditText
    private lateinit var TextInputLayout:TextInputLayout
    private lateinit var txtDescription: TextInputEditText
    private lateinit var txtLayoutDescription: TextInputLayout
    private lateinit var spinnerModel:Spinner
    private lateinit var spinnerBrand: Spinner
    private lateinit var spinnerState: Spinner
    private lateinit var spinnergouver: Spinner
    private lateinit var btnAdd: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_product)
    }
}