package com.example.learndatabase

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.learndatabase.setup.AppDatabase
import com.example.learndatabase.tugas.Tugas
import com.example.learndatabase.tugas.TugasDao
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var tugasDao: TugasDao
    private lateinit var db: AppDatabase

    private val tugasBaru = Tugas(judul = "Mabar", deskripsi = "mabar jam 10", tanggal = "5 juni")

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()

        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        tugasDao = db.tugasDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndGetTugas() {
        tugasDao.insertTugasTest(tugasBaru)
        val allTugas = tugasDao.getAllTugasTest()

        assert(allTugas.size == 1)
        assertEquals(allTugas[0].judul, "Mabar")
    }
}