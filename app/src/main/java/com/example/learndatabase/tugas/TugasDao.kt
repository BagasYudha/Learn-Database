package com.example.learndatabase.tugas

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TugasDao {
    @Insert
    suspend fun insertTugas(vararg tugas: Tugas)

    @Query("SELECT * FROM tugas")
    fun getAllTugas(): LiveData<List<Tugas>>
    
    @Insert
    fun insertTugasTest(tugas: Tugas): Long

    @Query("SELECT * FROM tugas")
    fun getAllTugasTest(): List<Tugas>
}
