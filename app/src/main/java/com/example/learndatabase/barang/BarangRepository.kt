package com.example.learndatabase.barang

import androidx.lifecycle.LiveData

class BarangRepository (private val barangDao: BarangDao) {
    val allBarang: LiveData<List<Barang>> = barangDao.getAllBarang()

    suspend fun insertBarangRep(barang: Barang) {
        barangDao.insertBarang(barang)
    }

}