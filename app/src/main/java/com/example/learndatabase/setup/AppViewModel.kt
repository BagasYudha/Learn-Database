package com.example.learndatabase.setup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.learndatabase.repository.TugasRepository
import com.example.learndatabase.tugas.Tugas
import kotlinx.coroutines.launch

class AppViewModel(application: Application) : AndroidViewModel(application) {

    // Inisialisasi repository
    private val tugasRepository: TugasRepository

    // LiveData dari tugasDao langsung diobservasi
    val allTugas: LiveData<List<Tugas>>

    init {
        val tugasDao = AppDatabase.getDatabase(application).tugasDao()

        tugasRepository = TugasRepository(tugasDao)

        allTugas = tugasRepository.allTugas
    }

    fun insertTugasVm(tugas: Tugas) {
        viewModelScope.launch {
            tugasRepository.insertTugasRep(tugas)
        }
    }
}