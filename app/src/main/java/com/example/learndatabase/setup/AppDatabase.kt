package com.example.learndatabase.setup

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.learndatabase.barang.Barang
import com.example.learndatabase.barang.BarangDao
import com.example.learndatabase.tugas.Tugas
import com.example.learndatabase.tugas.TugasDao

@Database(entities = [Tugas::class, Barang::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tugasDao(): TugasDao
    abstract fun barangDao(): BarangDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build().also { instance = it }
            }
    }
}
