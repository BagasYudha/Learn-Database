package com.example.learndatabase.barang

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_barang")
data class Barang(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nama_barang: String,
    val harga_barang: String
)