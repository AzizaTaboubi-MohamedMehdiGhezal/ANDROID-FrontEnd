package com.example.savy.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageSwitcher
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.savy.R

class home_fragment : Fragment(){


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //
        val imageSwitcher = view.findViewById<ImageSwitcher>(R.id.imgSwitcher)
        val context = requireContext()
        imageSwitcher.setFactory {
            val newSwitcher = ImageView(context)
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