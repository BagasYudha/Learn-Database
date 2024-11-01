package com.example.learndatabase.setup

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BarangDao {
    @Insert
    suspend fun insertBarang(barang: Barang)

    @Query("SELECT * FROM tb_barang")
    fun getAllBarang(): LiveData<List<Barang>>
}
