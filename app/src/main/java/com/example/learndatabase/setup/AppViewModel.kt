package com.example.learndatabase.setup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.learndatabase.note.Note
import com.example.learndatabase.note.NoteRepository
import com.example.learndatabase.repository.TugasRepository
import com.example.learndatabase.tugas.Tugas
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AppViewModel(application: Application) : AndroidViewModel(application) {

    // Inisialisasi repository
    private val tugasRepository: TugasRepository

    // Inisialisasi repository untuk Note
    private val noteRepository = NoteRepository()

    // Flow data untuk diobservasi di Fragment
    val notes: Flow<List<Note>> = noteRepository.notes

    // LiveData dari tugasDao langsung diobservasi
    val allTugas: LiveData<List<Tugas>>

    init {
        val tugasDao = AppDatabase.getDatabase(application).tugasDao()

        tugasRepository = TugasRepository(tugasDao)

        allTugas = tugasRepository.allTugas
    }

    fun insertTugasVm(tugas: Tugas) {
        viewModelScope.launch {
            tugasRepository.insertTugasRep(tugas)
        }
    }


    // Fungsi untuk menambahkan Note
    fun addNoteVm(note: Note) {
        viewModelScope.launch {
            noteRepository.addNote(note)
        }
    }

    // Fungsi untuk memperbarui Note
    fun updateNoteVm(note: Note) {
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    }

    // Fungsi untuk menghapus Note
    fun deleteNoteVm(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }
}