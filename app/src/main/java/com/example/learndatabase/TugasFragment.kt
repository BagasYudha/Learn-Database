package com.example.learndatabase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learndatabase.barang.Barang
import com.example.learndatabase.barang.BarangAdapter
import com.example.learndatabase.setup.AppViewModel
import com.example.learndatabase.tugas.Tugas
import com.example.learndatabase.tugas.TugasAdapter
import com.example.learndatabase.databinding.FragmentTugasBinding

class TugasFragment : Fragment() {

    private lateinit var binding: FragmentTugasBinding
    private lateinit var appViewModel: AppViewModel
    private lateinit var tugasAdapter: TugasAdapter
    private lateinit var barangAdapter: BarangAdapter

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


//        Tugas
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
            val awokwo = binding.tanggalEditText.text.toString()

            val tugas = Tugas(judul = judul, deskripsi = deskripsi, tanggal = awokwo)
            appViewModel.insertTugas(tugas)
        }


        //        Barang
        barangAdapter = BarangAdapter(listOf())

        binding.recyclerViewBarang.adapter = barangAdapter
        binding.recyclerViewBarang.layoutManager = LinearLayoutManager(requireContext())

        appViewModel.allBarang.observe(viewLifecycleOwner) { barang ->
                barangAdapter.updateBarang(barang)
            }

        binding.addButtonBarang.setOnClickListener{
            val barangBaru = binding.namaBarangEditText.text.toString()
            val hargaNya = binding.hargaBarangEditText.text.toString()

            val barang = Barang(nama_barang = barangBaru, harga_barang = hargaNya)
            appViewModel.insertBarang(barang)

        }
    }
}
