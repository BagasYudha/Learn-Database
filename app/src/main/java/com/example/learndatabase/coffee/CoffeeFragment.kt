package com.example.learndatabase.coffee

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learndatabase.databinding.FragmentCoffeeBinding
import com.example.learndatabase.databinding.FragmentTugasBinding
import com.example.learndatabase.setup.AppViewModel
import com.example.learndatabase.tugas.TugasAdapter
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoffeeFragment : Fragment() {

    private lateinit var binding: FragmentCoffeeBinding

    private val coffeeAPIService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.sampleapis.com/coffee/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoffeeAPIService::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoffeeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCoffeeList.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            val coffeeList = coffeeAPIService.getCoffee()
            binding.rvCoffeeList.adapter = CoffeeAdapter(coffeeList)
        }
    }
}
