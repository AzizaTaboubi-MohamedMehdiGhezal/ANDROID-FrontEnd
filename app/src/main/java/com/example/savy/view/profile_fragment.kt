package com.example.savy.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.savy.MainActivity
import com.example.savy.R
import com.example.savy.model.User
import com.example.savy.utils.Constant
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class profile_fragment : Fragment(){

    private lateinit var profileImg: ImageView
    private lateinit var name_txt: TextView
    private lateinit var updateBtn: Button
    private lateinit var productsBtn: Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{

        val view = inflater.inflate(R.layout.profile_fragment, container, false)
                //INIT
                profileImg = view.findViewById(R.id.profileImg)
                name_txt = view.findViewById(R.id.name_txt)
                updateBtn = view.findViewById(R.id.updateBtn)
                productsBtn = view.findViewById(R.id.productsBtn)
        //Var
        var FieldsState = false
        val context = requireContext()
        //getting info from sharedpref
        val sharedPreferences =
            context.getSharedPreferences(Constant.SHARED_PREF_SESSION, Context.MODE_PRIVATE)
        val userData = sharedPreferences.getString("USER_DATA", "")
        if (userData != null) {
        val user = Gson().fromJson(userData, User::class.java)
           // profileImg.setImageURI(user.profilepic)
            name_txt.setText(user.fullname)
            updateBtn.setOnClickListener {
                val intent = Intent(context, update_profile::class.java)
                startActivity(intent)
                requireActivity().finish()
        }
            productsBtn.setOnClickListener{
            val intent = Intent(context, products::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
       }

    return view
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