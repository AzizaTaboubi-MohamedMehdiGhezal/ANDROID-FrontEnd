package com.example.savy.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.FrameLayout
import android.widget.ImageSwitcher
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.savy.R

class usedAllActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.used_all)
        val imageSwitcher = findViewById<ImageSwitcher>(R.id.imgSwitcher)
        imageSwitcher.setFactory {
            val newSwitcher = ImageView(this@usedAllActivity)
            newSwitcher.scaleType = ImageView.ScaleType.CENTER_CROP
            newSwitcher.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            newSwitcher
        }
        val images = listOf(
            R.drawable.pub_2,
            R.drawable.laptop_1,
            R.drawable.access,
            R.drawable.phone_1,

            )

        var currentIndex = 0

        val handler = Handler(Looper.getMainLooper())
        handler.post(object : Runnable {
            override fun run() {
                currentIndex = (currentIndex + 1) % images.size
                imageSwitcher.setImageResource(images[currentIndex])
                handler.postDelayed(this, 2000)
            }
        })

    }
}