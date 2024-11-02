package com.example.learndatabase.setup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.learndatabase.barang.Barang
import com.example.learndatabase.tugas.Tugas
import kotlinx.coroutines.launch

class AppViewModel(application: Application) : AndroidViewModel(application) {

    // Deklarasi variabel dao
    private val tugasDao = AppDatabase.getDatabase(application).tugasDao()
    private val barangDao= AppDatabase.getDatabase(application).barangDao()

    // LiveData dari tugasDao langsung diobservasi
    val allTugas: LiveData<List<Tugas>> = tugasDao.getAllTugas()
    val allBarang: LiveData<List<Barang>> = barangDao.getAllBarang()


    fun insertTugas(tugas: Tugas) {
        viewModelScope.launch {
            tugasDao.insertTugas(tugas)
        }
    }

    fun insertBarang(barang: Barang) {
        viewModelScope.launch {
            barangDao.insertBarang(barang)
        }
    }
}