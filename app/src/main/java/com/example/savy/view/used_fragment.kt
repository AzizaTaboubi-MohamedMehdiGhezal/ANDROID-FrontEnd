package com.example.savy.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.savy.MainActivity
import com.example.savy.R

class used_fragment  : Fragment(){
    private lateinit var browseAll:Button
    private lateinit var seekBarPrice: SeekBar
    private lateinit var fromTxt: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.used_fragment, container, false)
        seekBarPrice = view.findViewById(R.id.seekBarPrice)
        fromTxt = view.findViewById(R.id.fromTxt)
        seekBarPrice.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // Calculate the price based on the progress value.
                val price = progress * 50

                // Display the price in a TextView.
                fromTxt.text = "From 0 to : $price"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do nothing.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Do nothing.
            }
        })
        browseAll = view.findViewById(R.id.browseAll)
        val context = requireContext()



        browseAll.setOnClickListener{
            val intent = Intent(context, usedAllActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }



        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //

    }
}