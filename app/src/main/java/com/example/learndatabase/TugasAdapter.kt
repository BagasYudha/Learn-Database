package com.example.learndatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learndatabase.databinding.ItemTugasBinding
import com.example.learndatabase.setup.Tugas

class TugasAdapter(private var tugas: List<Tugas>) :
    RecyclerView.Adapter<TugasAdapter.TugasViewHolder>() {

    inner class TugasViewHolder(private val binding: ItemTugasBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Tugas) {
            binding.titleTextView.text = task.judul
            binding.deskripsiTextView.text = task.deskripsi
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TugasViewHolder {
        val binding = ItemTugasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TugasViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TugasViewHolder, position: Int) {
        holder.bind(tugas[position])
    }

    override fun getItemCount(): Int = tugas.size

    fun updateTugas(newTugas: List<Tugas>) {
        tugas = newTugas
        notifyDataSetChanged()
    }
}