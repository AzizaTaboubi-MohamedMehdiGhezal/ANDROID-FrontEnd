package com.example.savy.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.savy.R
import com.example.savy.viewmodel.UserViewModel
import org.json.JSONObject

class forgotpasswordActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password)

        val buttonClick = findViewById<Button>(R.id.getotpBtn)
        buttonClick.setOnClickListener {
            val jsonString = """{"email": "aziza.taboubi@esprit.tn"}"""
            val jsonObject = JSONObject(jsonString)
            val viewModel: UserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            viewModel.forgotpwd( jsonObject )
            println(jsonObject)
            val intent = Intent(this, otpActivity::class.java)
            startActivity(intent)
        }

    }

}