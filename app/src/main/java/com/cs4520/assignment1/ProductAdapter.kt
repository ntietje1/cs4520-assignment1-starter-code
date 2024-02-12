package com.cs4520.assignment1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private val productList: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(productView: View) : RecyclerView.ViewHolder(productView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val typeImage: ImageView = itemView.findViewById(R.id.typeImage)
        val expiryDate: TextView = itemView.findViewById(R.id.expiryDate)
        val price: TextView = itemView.findViewById(R.id.price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.name.text = product.name
        holder.expiryDate.text = product.expiryDate ?: ""
        holder.price.text = "$" + product.price.toString()

        if (product.type == "Equipment") {
            holder.itemView.setBackgroundColor(0xFFE06666.toInt())
            holder.typeImage.setImageResource(R.drawable.equipment)
        } else if (product.type == "Food") {
            holder.itemView.setBackgroundColor(0xFFFFD965.toInt())
            holder.typeImage.setImageResource(R.drawable.food)
        }
    }
}