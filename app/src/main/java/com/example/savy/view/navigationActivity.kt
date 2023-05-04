package com.example.savy.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.savy.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class navigationActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.home_fragment)
        loadFragment(home_fragment())

        val bottomNav = findViewById<BottomNavigationView>(R.id.bott_nav)

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    loadFragment(home_fragment())
                    true
                }
                R.id.nav_new -> {
                    loadFragment(new_fragment())
                    true
                }
                R.id.nav_used -> {
                    loadFragment(used_fragment())
                    true
                }
                R.id.nav_profile -> {
                    loadFragment(profile_fragment())
                    true
                }


                else -> {false}
            }
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}