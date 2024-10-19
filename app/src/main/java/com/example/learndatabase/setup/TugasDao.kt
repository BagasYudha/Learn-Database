package com.example.learndatabase.setup

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TugasDao {
    @Insert
    suspend fun insertTugas(tugas: Tugas)

    @Query("SELECT * FROM tugas")
    fun getAllTugas(): LiveData<List<Tugas>>
}
