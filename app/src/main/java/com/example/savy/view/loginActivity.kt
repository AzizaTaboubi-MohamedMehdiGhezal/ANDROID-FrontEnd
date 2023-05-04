package com.example.savy.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
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
import com.example.savy.GoogleLogin
import com.example.savy.services.APIService
import com.example.savy.services.userService
import com.example.savy.utils.Constant
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 100


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

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        // Set the dimensions of the sign-in button.
        val signInButton = findViewById<SignInButton>(R.id.sign_in_button)
        signInButton.setSize(SignInButton.SIZE_STANDARD)

        signInButton.setOnClickListener {
            signIn()
        }


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

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val acct = GoogleSignIn.getLastSignedInAccount(this)
            if (acct != null) {
                val personName = acct.displayName
                val personGivenName = acct.givenName
                val personFamilyName = acct.familyName
                val personEmail = acct.email
                val personId = acct.id
                val personPhoto = acct.photoUrl
            }

            startActivity(Intent(this@LoginActivity, GoogleLogin::class.java))


        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("message", e.toString())
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