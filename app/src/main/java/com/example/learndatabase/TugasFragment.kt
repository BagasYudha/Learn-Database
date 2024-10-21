package com.example.learndatabase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learndatabase.databinding.FragmentTugasBinding
import com.example.learndatabase.setup.Tugas


class TugasFragment : Fragment() {

    private lateinit var binding: FragmentTugasBinding
    private lateinit var tugasViewModel: AppViewModel
    private lateinit var tugasAdapter: TugasAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTugasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tugasViewModel = ViewModelProvider(this).get(AppViewModel::class.java)
        tugasAdapter = TugasAdapter(listOf())

        // Setup RecyclerView
        binding.recyclerView.adapter = tugasAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observasi data dari ViewModel
        tugasViewModel.allTugas.observe(viewLifecycleOwner) { tugas ->
            tugasAdapter.updateTugas(tugas)
        }

        // Tambahkan tugas baru
        binding.addButton.setOnClickListener {
            val judul = binding.judulEditText.text.toString()
            val deskripsi = binding.deskripsiEditText.text.toString()
            val tugas = Tugas(judul = judul, deskripsi = deskripsi)
            tugasViewModel.insertTugas(tugas)
        }
    }
}
