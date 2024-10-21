package com.example.learndatabase.setup

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Tugas::class, Barang::class], version = 1)
abstract class TugasDatabase : RoomDatabase() {
    abstract fun tugasDao(): TugasDao
    abstract fun barangDao(): BarangDao

    companion object {
        @Volatile
        private var instance: TugasDatabase? = null

        fun getDatabase(context: Context): TugasDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    TugasDatabase::class.java,
                    "tugas_database"
                ).build().also { instance = it }
            }
    }
}
