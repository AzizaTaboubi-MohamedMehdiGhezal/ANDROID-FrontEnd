package com.example.savy.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.savy.MainActivity
import com.example.savy.R
import com.example.savy.services.APIService
import com.example.savy.services.userService
import com.example.savy.utils.Constant
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

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
        txtLogin = findViewById(R.id.txtLogin)
        txtLayoutLogin = findViewById(R.id.txtLayoutLogin)
        txtPassword = findViewById(R.id.txtPassword)
        txtLayoutPassword = findViewById(R.id.txtLayoutPassword)
        cbRememberMe = findViewById(R.id.cbRememberMe)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignup = findViewById(R.id.btnSignup)
        fgtpwdBtn = findViewById(R.id.fgtpwdBtn)
        btnJustBrowsing = findViewById(R.id.btnJustBrowsing)
        supportActionBar?.hide()
        setFullScreen(context)
        btnSignup.setOnClickListener{
            var intent= Intent(this,signupActivity::class.java)
            startActivity(intent)
        }

        fgtpwdBtn.setOnClickListener{
            var intent = Intent(this, forgotpasswordActivity::class.java)
            startActivity(intent)
        }


        btnLogin.setOnClickListener{
            APIService.userService.signIn(
                userService.LoginBody(
                    txtLogin.text.toString(),
                    txtPassword.text.toString()
                )
            ).enqueue( object : Callback<userService.UserResponse> {
                override fun onResponse(
                    call: Call<userService.UserResponse>,
                    response: Response<userService.UserResponse>
                ) {
                    if (response.code() == 200) {
                        if(cbRememberMe.isChecked) {
                            val sharedPreferences =
                                getSharedPreferences(Constant.SHARED_PREF_SESSION, MODE_PRIVATE)
                            val sharedPreferencesEditor: SharedPreferences.Editor =
                                sharedPreferences.edit()
                            val json = Gson().toJson(response.body()!!.user)
                            sharedPreferencesEditor.putString("USER_DATA", json)
                            sharedPreferencesEditor.apply()
                        }
                        val intent =
                            Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else if(response.code() == 403) {
                        showDialog(context,"Please Verify your account on ${txtLogin.text.toString()}")
                    }
                    else if(response.code() == 400) {
                        showDialog(context,"Wrong password ❌")
                    }
                    else {
                        println("status code is " + response.code())
                    }
                }

                override fun onFailure(call: Call<userService.UserResponse>, t: Throwable) {

                    println("HTTP ERROR")
                    t.printStackTrace()}

            })
        }
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


    fun showDialog(activityName: Context, message:String){
        val builder = AlertDialog.Builder(activityName)
        builder.setTitle("Caution ⚠️")
        builder.setMessage(message)
        builder.setPositiveButton("OK", null)
        val dialog = builder.create()
        dialog.show()
    }

    private fun emailVerified():Boolean {
        if(!Patterns.EMAIL_ADDRESS.matcher(txtLogin.text).matches()){
            (txtLogin.parent.parent as TextInputLayout).isErrorEnabled = true
            (txtLogin.parent.parent as TextInputLayout).error = getString(R.string.check_email)
            return false
        }
        return true
    }

    private fun setError(et: TextInputEditText, errorMsg: String): Boolean {
        if(et.text?.isEmpty() == true){
            (et.parent.parent as TextInputLayout).isErrorEnabled = true
            (et.parent.parent as TextInputLayout).error = errorMsg
            return true
        }else{
            (et.parent.parent as TextInputLayout).isErrorEnabled = false
            return false
        }
    }
}