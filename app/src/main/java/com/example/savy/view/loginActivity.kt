package com.example.savy.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.lifecycle.ViewModelProvider
import com.example.savy.R
import com.example.savy.viewmodel.UserViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class loginActivity : AppCompatActivity() {

    private lateinit var txtLogin : TextInputEditText
    private lateinit var txtPassword : TextInputEditText
    private lateinit var txtLayoutLogin : TextInputLayout
    private lateinit var txtLayoutPassword : TextInputLayout
    private lateinit var cbRememberMe : CheckBox
    private lateinit var btnLogin : Button
    private lateinit var btnSignup : Button
    private lateinit var fgtpwdBtn : Button
    private lateinit var btnJustBrowsing : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Var
        val context = this@LoginActivity
        //INIT UI ELEMENTS
        txtLogin =findViewById(R.id.txtLogin)
        txtLayoutLogin =findViewById(R.id.txtLayoutLogin)
        txtPassword =findViewById(R.id.txtPassword)
        txtLayoutPassword =findViewById(R.id.txtLayoutPassword)
        cbRememberMe =findViewById(R.id.cbRememberMe)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignup = findViewById(R.id.btnSignup)
        fgtpwdBtn = findViewById(R.id.fgtpwdBtn)
        btnJustBrowsing = findViewById(R.id.btnJustBrowsing)
        val viewModel: UserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.login(LoginRequest("aziza.taboubi@esprit.tn", "graygraygray")) {

        }
        val buttonClick = findViewById<Button>(R.id.fgtpwdBtn)
        buttonClick.setOnClickListener {
            val intent = Intent(this, forgotpasswordActivity::class.java)
            startActivity(intent)
        }
        val buttoncl = findViewById<Button>(R.id.btnLogin)
        buttoncl.setOnClickListener {
            val intent = Intent(this, navigationActivity::class.java)
            startActivity(intent)
        }
    }
}