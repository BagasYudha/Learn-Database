package com.example.learndatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.learndatabase.setup.TugasDatabase
import com.example.learndatabase.setup.Tugas
import kotlinx.coroutines.launch

class TugasViewModel(application: Application) : AndroidViewModel(application) {

    private val tugasDao = TugasDatabase.getDatabase(application).tugasDao()

    // LiveData dari tugasDao langsung diobservasi
    val allTugas: LiveData<List<Tugas>> = tugasDao.getAllTugas()

    fun insertTugas(tugas: Tugas) {
        viewModelScope.launch {
            tugasDao.insertTugas(tugas)
        }
    }
}