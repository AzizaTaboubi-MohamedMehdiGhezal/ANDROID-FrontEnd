package com.example.savy.view

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.savy.CustomAdapter
import com.example.savy.OnListProdClick
import com.example.savy.R
import com.example.savy.model.ScrappedProduct
import com.example.savy.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.recyclerview_newprod.*


class newProduct : AppCompatActivity(), OnListProdClick {

    private lateinit var productViewModel: ProductViewModel
    lateinit var customAdapter: CustomAdapter
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var imageSearch: ImageView
    lateinit var editSearch: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recyclerview_newprod) //new_prod_scrap

        toolbar = findViewById(R.id.toolbar)
        imageSearch = toolbar.findViewById(R.id.imageSearch)
        editSearch = toolbar.findViewById(R.id.editSearch)
        setSupportActionBar(toolbar)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)


        val prods = ArrayList<ScrappedProduct>()

        recyclerViewProduct.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        val adapter = CustomAdapter(prods)
        recyclerViewProduct.adapter = adapter

        imageSearch.setOnClickListener{
            val search = editSearch.text.toString()
           // customAdapter
        }

    }

    override fun onItemClick(scrappedProduct: ScrappedProduct) {
        TODO("Not yet implemented")
    }

}
/*
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)

    binding.apply {
        recyclerView.adapter = restaurantRecyclerView
        viewModel.getAllRestaurants()
    }

    restaurantRecyclerView.onListItemClick = this

    binding.apply {
        viewModel.restaurantsLiveData.observe(viewLifecycleOwner,
            Observer {
                if (it != null) {
                    restaurantRecyclerView.setList(it)
                } else {
                    Toast.makeText(
                        context,
                        "Connection Failed",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        )

        viewModel.messageLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                if(it == "Server error, please try again later."){
                    Toast.makeText(
                        context,
                        it,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }
}

override fun onItemClick(restaurant: Restaurant) {
    val action = RestaurantsFragmentDirections.actionRestaurantsFragmentToRestaurantDetailsFragment(restaurant.id)
    findNavController().navigate(action)
}*/
