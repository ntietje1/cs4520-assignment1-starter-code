package com.cs4520.assignment1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.cs4520.assignment1.data.Product
import com.cs4520.assignment1.R
import com.cs4520.assignment1.data.db.ProductDatabase
import com.cs4520.assignment1.data.http.RetrofitInstance
import com.cs4520.assignment1.views.ProductAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.product_view, container, false)
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
        val noProducts: View = view.findViewById(R.id.noProducts)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        progressBar.isVisible = true

        val db = Room.databaseBuilder(
            requireContext(),
            ProductDatabase::class.java, "database-name"
        ).build()

        val productDao = db.productDao()
        var products: List<Product> = listOf()

        RetrofitInstance.api.getProducts(3).enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                progressBar.isVisible = false
                if (response.isSuccessful) {
                    products = response.body()!!
                    products = products.filter { it.type != null && it.price != null}
                    products = products.distinctBy { it.name }

                    viewLifecycleOwner.lifecycleScope.launch {
                        withContext(Dispatchers.IO) {
                            productDao.deleteAll()
                            productDao.insertAll(products)
                        }
                    }

                    recyclerView.adapter = ProductAdapter(products)
                } else {
                    println("Error: ${response.code()}")
                }
                noProducts.isVisible = products.isEmpty()
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                viewLifecycleOwner.lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        products = productDao.get()
                    }
                    withContext(Dispatchers.Main) {
                        progressBar.isVisible = false
                        recyclerView.adapter = ProductAdapter(products)
                        noProducts.isVisible = products.isEmpty()
                    }
                }
            }
        })
        return view
    }
}