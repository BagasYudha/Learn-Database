package com.example.learndatabase.setup

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tugas")
data class Tugas(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val judul: String,
    val deskripsi: String
)