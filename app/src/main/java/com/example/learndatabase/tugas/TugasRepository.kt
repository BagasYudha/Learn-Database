package com.example.learndatabase.repository

import androidx.lifecycle.LiveData
import com.example.learndatabase.tugas.Tugas
import com.example.learndatabase.tugas.TugasDao

class TugasRepository(private val tugasDao: TugasDao) {

    val allTugas: LiveData<List<Tugas>> = tugasDao.getAllTugas()

    suspend fun insertTugasRep(tugas: Tugas) {
        tugasDao.insertTugas(tugas)
    }
}
