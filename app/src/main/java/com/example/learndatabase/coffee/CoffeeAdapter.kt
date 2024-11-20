package com.example.learndatabase.coffee

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.learndatabase.R
import com.example.learndatabase.databinding.ItemCoffeeBinding

class CoffeeAdapter(private val coffeeList: List<Coffee>) :
    RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder>() {

    inner class CoffeeViewHolder(private val binding: ItemCoffeeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(coffee: Coffee) {
            binding.apply {
                Glide.with(ivCoffee.context)
                    .load(coffee.image)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_image)
                    .into(ivCoffee)
                tvTitle.text = coffee.title
                tvDescription.text = coffee.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeViewHolder {
        val binding = ItemCoffeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoffeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoffeeViewHolder, position: Int) {
        holder.bind(coffeeList[position])
    }

    override fun getItemCount(): Int = coffeeList.size
}
