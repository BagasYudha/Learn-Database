package com.example.learndatabase.barang

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learndatabase.databinding.ItemBarangBinding

class BarangAdapter(private var barang: List<Barang>) :
    RecyclerView.Adapter<BarangAdapter.BarangViewHolder>() {

        inner class BarangViewHolder(private val binding: ItemBarangBinding) :
                RecyclerView.ViewHolder(binding.root) {
                    fun bind(task: Barang) {
                binding.namaBarangTextView.text = task.nama_barang
                binding.hargaBarangTextView.text = task.harga_barang
                }
        }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): BarangViewHolder {
        val binding = ItemBarangBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BarangViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        holder.bind(barang[position])
    }

    override fun getItemCount(): Int {
        return barang.size
    }


    fun updateBarang(newBarang: List<Barang>) {
        barang = newBarang
        notifyDataSetChanged()
    }
}