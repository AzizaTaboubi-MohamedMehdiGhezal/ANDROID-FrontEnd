package com.example.savy

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.savy.model.LoginRequest
import com.example.savy.model.LoginResponse
import com.example.savy.view.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setFullScreen(this@MainActivity)
        setContentView(R.layout.main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val iconColor = ContextCompat.getColorStateList(this, R.color.green_main)
        bottomNavigationView.setItemIconTintList(iconColor)
        val bgColor = ContextCompat.getColor(this, R.color.black)
        val textColor = ContextCompat.getColorStateList(this, R.color.green_main)
        bottomNavigationView.setItemTextColor(textColor)
        bottomNavigationView.setBackground(ColorDrawable(bgColor))
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val fragment = home_fragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, fragment)
                        .commit()
                    true
                }
                R.id.nav_new -> {
                    val fragment = new_fragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, fragment)
                        .commit()
                    true
                }
                R.id.nav_used -> {
                    val fragment = used_fragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, fragment)
                        .commit()
                    true
                }
                R.id.nav_profile -> {
                    val fragment = profile_fragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, fragment)
                        .commit()
                    true
                }
                R.id.nav_settings -> {
                    val fragment = settings_fragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, fragment)
                        .commit()
                    true
                }
                else -> false
            }
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
}