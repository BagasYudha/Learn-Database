package com.example.learndatabase.tugas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learndatabase.databinding.ItemTugasBinding
import com.example.learndatabase.databinding.ItemTugasPerioritasBinding

enum class ITEM_VIEW_TYPE { NORMAL, PERIORITAS }

class TugasAdapter(private var tugas: List<Tugas>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class TugasViewHolder(private val binding: ItemTugasBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Tugas) {
            binding.titleTextView.text = task.judul
            binding.deskripsiTextView.text = task.deskripsi
            binding.tanggalTextView.text = task.tanggal
        }
    }

    inner class TugasPerioritasViewHolder(private val binding: ItemTugasPerioritasBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Tugas) {
            binding.titleTextView.text = task.judul
            binding.deskripsiTextView.text = task.deskripsi
            binding.tanggalTextView.text = task.tanggal
            binding.periorityTextView.text = "Prioritas"
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (tugas[position].isPrioritas) {
            ITEM_VIEW_TYPE.PERIORITAS.ordinal
        } else {
            ITEM_VIEW_TYPE.NORMAL.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE.PERIORITAS.ordinal -> {
                val binding = ItemTugasPerioritasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                TugasPerioritasViewHolder(binding)
            }
            else -> {
                val binding = ItemTugasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                TugasViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val task = tugas[position]
        when (holder) {
            is TugasViewHolder -> holder.bind(task)
            is TugasPerioritasViewHolder -> holder.bind(task)
        }
    }

    override fun getItemCount(): Int = tugas.size

    fun updateTugas(newTugas: List<Tugas>) {
        tugas = newTugas
        notifyDataSetChanged()
    }
}
