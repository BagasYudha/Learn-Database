package com.example.learndatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.learndatabase.setup.Barang
import com.example.learndatabase.setup.TugasDatabase
import com.example.learndatabase.setup.Tugas
import kotlinx.coroutines.launch

class AppViewModel(application: Application) : AndroidViewModel(application) {

    // Deklarasi variabel dao
    private val tugasDao = TugasDatabase.getDatabase(application).tugasDao()
    private val barangDao= TugasDatabase.getDatabase(application).barangDao()

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