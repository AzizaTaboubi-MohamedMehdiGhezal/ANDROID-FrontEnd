package com.example.savy.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.savy.MainActivity
import com.example.savy.R
import kotlinx.android.synthetic.main.home_fragment.*

class home_fragment : Fragment(){


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        imageSwitcher2.setOnClickListener {
            var intent = Intent(context, newProduct::class.java)
            startActivity(intent)
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //

    }
}