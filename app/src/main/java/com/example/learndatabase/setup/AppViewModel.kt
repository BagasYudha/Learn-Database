package com.example.learndatabase.setup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.learndatabase.barang.Barang
import com.example.learndatabase.barang.BarangRepository
import com.example.learndatabase.repository.TugasRepository
import com.example.learndatabase.tugas.Tugas
import kotlinx.coroutines.launch

class AppViewModel(application: Application) : AndroidViewModel(application) {

    // Inisialisasi repository
    private val tugasRepository: TugasRepository
    private val barangRepository: BarangRepository

    // LiveData dari tugasDao langsung diobservasi
    val allTugas: LiveData<List<Tugas>>
    val allBarang: LiveData<List<Barang>>

    init {
        val tugasDao = AppDatabase.getDatabase(application).tugasDao()
        val barangDao = AppDatabase.getDatabase(application).barangDao()

        tugasRepository = TugasRepository(tugasDao)
        barangRepository = BarangRepository(barangDao)

        allTugas = tugasRepository.allTugas
        allBarang = barangRepository.allBarang
    }

    fun insertTugasVm(tugas: Tugas) {
        viewModelScope.launch {
            tugasRepository.insertTugasRep(tugas)
        }
    }

    fun insertBarangVm(barang: Barang) {
        viewModelScope.launch {
            barangRepository.insertBarangRep(barang)
        }
    }

}