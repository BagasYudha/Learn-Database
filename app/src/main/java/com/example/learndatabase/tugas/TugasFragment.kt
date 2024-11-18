package com.example.learndatabase.tugas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learndatabase.setup.AppViewModel
import com.example.learndatabase.databinding.FragmentTugasBinding

class TugasFragment : Fragment() {

    private lateinit var binding: FragmentTugasBinding
    private lateinit var appViewModel: AppViewModel
    private lateinit var tugasAdapter: TugasAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTugasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appViewModel = ViewModelProvider(this).get(AppViewModel::class.java)

        // Tugas
        tugasAdapter = TugasAdapter(listOf())

        // Setup RecyclerView
        binding.recyclerView.adapter = tugasAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observasi data dari ViewModel
        appViewModel.allTugas.observe(viewLifecycleOwner) { tugas ->
            tugasAdapter.updateTugas(tugas)
        }

        // Tambahkan tugas baru
        binding.addButton.setOnClickListener {
            val judul = binding.judulEditText.text.toString()
            val deskripsi = binding.deskripsiEditText.text.toString()
            val tanggal = binding.tanggalEditText.text.toString()
            val isPrioritas = binding.prioritasCheckBox.isChecked

            if (judul.isNotEmpty() && deskripsi.isNotEmpty() && tanggal.isNotEmpty()) {
                val tugas = Tugas(judul = judul, deskripsi = deskripsi, tanggal = tanggal, isPrioritas = isPrioritas)
                appViewModel.insertTugasVm(tugas)
            } else {
                // Tambahkan logika untuk menampilkan pesan error jika ada input kosong
            }
        }
    }
}
