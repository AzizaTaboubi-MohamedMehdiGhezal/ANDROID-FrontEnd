package com.example.savy.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.savy.R
import com.example.savy.model.User
import com.example.savy.services.APIService
import com.example.savy.services.userService
import com.example.savy.utils.Constant
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class update_profile: AppCompatActivity() {

    private lateinit var fullnameEdit : TextInputEditText
    private lateinit var fullnameLyt : TextInputLayout
    private lateinit var numtelEdit : TextInputEditText
    private lateinit var numtelLyt : TextInputLayout
    private lateinit var emailEdit : TextInputEditText
    private lateinit var emailLyt : TextInputLayout
    private lateinit var btnConfirm : Button
    private lateinit var btnChangePwd : Button
    override fun onCreate(savedInstanceState: Bundle?) {

        //Val
        val context = this@update_profile
        fullnameEdit = findViewById(R.id.fullnameEdit)
        numtelEdit = findViewById(R.id.numtelEdit)
        emailEdit = findViewById(R.id.emailEdit)
        emailLyt = findViewById(R.id.emailLyt)
        numtelLyt = findViewById(R.id.numtelLyt)
        fullnameLyt = findViewById(R.id.fullnameLyt)
        btnConfirm = findViewById(R.id.btnConfirm)
        btnChangePwd = findViewById(R.id.btnChangePwd)

            super.onCreate(savedInstanceState)
            setContentView(R.layout.update_profile)

        //getting info from sharedpref
        val sharedPreferences =
            context.getSharedPreferences(Constant.SHARED_PREF_SESSION, MODE_PRIVATE)
        val userData = sharedPreferences.getString("USER_DATA", "")
        if (userData != null) {
            val user = Gson().fromJson(userData, User::class.java)
            emailEdit.setText(user.email)
            numtelEdit.setText(user.numTel)
            fullnameEdit.setText(user.email)
            btnChangePwd.setOnClickListener {
                val intent = Intent(context, resetpwdActivity::class.java)
                startActivity(intent)
            }

            btnConfirm.setOnClickListener {
                APIService.userService.updateProfile(
                    userService.updateProfileBody(
                        emailEdit.text.toString(),
                        numtelEdit.text.toString(),
                        fullnameEdit.text.toString(),
                        user.token
                    )
                ).enqueue(object : Callback<userService.UserResponse> {
                    override fun onResponse(
                        call: Call<userService.UserResponse>,
                        response: Response<userService.UserResponse>
                    ) {
                        if (response.code() == 200) {
                            Toast.makeText(context, "Profile Updated ✅", Toast.LENGTH_SHORT).show()
                        } else if (response.code() == 400) {
                            println("status code is " + response.message())
                            showDialog(context, "Error ❌")
                        } else {
                            println("status code is " + response.code())
                        }
                    }

                    override fun onFailure(call: Call<userService.UserResponse>, t: Throwable) {

                        println("HTTP ERROR")
                        t.printStackTrace()
                    }

                })
            }

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
}

