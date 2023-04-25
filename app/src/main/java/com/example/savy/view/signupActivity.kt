package com.example.savy.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.savy.R
import com.example.savy.services.APIService
import com.example.savy.services.userService
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class signupActivity : AppCompatActivity(){
    private lateinit var txtName : TextInputEditText
    private lateinit var txtLayoutName : TextInputLayout
    private lateinit var txtTelNum : TextInputEditText
    private lateinit var txtLayoutTelNum : TextInputLayout
    private lateinit var txtMailAddress : TextInputEditText
    private lateinit var txtLayoutMailAddress : TextInputLayout
    private lateinit var txtPassword : TextInputEditText
    private lateinit var txtLayoutPassword : TextInputLayout
    private lateinit var txtConfirmPassword : TextInputEditText
    private lateinit var txtLayoutConfirmPassword : TextInputLayout
    private lateinit var btnSignup : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        //Var
        val context = this@signupActivity
        supportActionBar?.hide()
        setFullScreen(context)
        txtName = findViewById(R.id.txtName)
        txtLayoutName = findViewById(R.id.txtLayoutName)
        txtTelNum = findViewById(R.id.txtTelNum)
        txtLayoutTelNum = findViewById(R.id.txtLayoutTelNum)
        txtMailAddress = findViewById(R.id.txtMailAddress)
        txtLayoutMailAddress = findViewById(R.id.txtLayoutMailAddress)
        txtPassword = findViewById(R.id.txtPassword)
        txtLayoutPassword = findViewById(R.id.txtLayoutPassword)
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword)
        txtLayoutConfirmPassword = findViewById(R.id.txtLayoutConfirmPassword)
        btnSignup = findViewById(R.id.btnSignup)


        btnSignup!!.setOnClickListener {
            APIService.userService.signUp(
                userService.UserBody(
                    txtName.text.toString(),
                    txtMailAddress.text.toString(),
                    txtPassword.text.toString(),
                    txtTelNum.text.toString(),


                )
            ).enqueue(
                object : Callback<userService.UserResponse> {
                    override fun onResponse(
                        call: Call<userService.UserResponse>,
                        response: Response<userService.UserResponse>
                    ) {
                        if (response.code() == 201) {
                            APIService.userService.sendVerification(
                                userService.emailBody(txtMailAddress.text.toString())
                            ).enqueue(
                                object : Callback<userService.UserResponse> {
                                    override fun onResponse(
                                        call: Call<userService.UserResponse>,
                                        response: Response<userService.UserResponse>
                                    ) {
                                        val intent =
                                            Intent(this@signupActivity, LoginActivity::class.java)
                                        startActivity(intent)
                                        finish()
                                        println("status code is " + response.code())
                                    }
                                    override fun onFailure(call: Call<userService.UserResponse>, t: Throwable) {
                                        println("HTTP ERROR")
                                        t.printStackTrace()
                                    }
                                }
                            )

                        } else if (response.code() == 403) {
                            showDialog(context)
                        } else {
                            println("status code is " + response.code())
                        }
                    }

                    override fun onFailure(call: Call<userService.UserResponse>, t: Throwable) {

                        println("HTTP ERROR")
                        t.printStackTrace()
                    }

                }
            )

        }

    }


    private fun showDialog(activityName: Context){
        val builder = AlertDialog.Builder(this@signupActivity)
        builder.setTitle("Caution ⚠️")
        builder.setMessage("User already exist")
        builder.setPositiveButton("OK", null)
        val dialog = builder.create()
        dialog.show()
    }
    fun setFullScreen(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity.window.insetsController?.apply {
                hide(WindowInsets.Type.statusBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            activity.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }

}