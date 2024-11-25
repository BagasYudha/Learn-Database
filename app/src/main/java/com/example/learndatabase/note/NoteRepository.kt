package com.example.learndatabase.note

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NoteRepository {

    private val database = FirebaseDatabase.getInstance()
    private val noteRef = database.getReference("notes")

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> get() = _notes

    init {
        // Listen to data changes in Firebase
        noteRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val fetchedNotes = snapshot.children.mapNotNull { it.getValue(Note::class.java) }
                _notes.value = fetchedNotes
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    fun addNote(note: Note) {
        note.id = noteRef.push().key
        note.id?.let { noteRef.child(it).setValue(note) }
    }

    fun updateNote(note: Note) {
        note.id?.let { noteRef.child(it).setValue(note) }
    }

    fun deleteNote(note: Note) {
        note.id?.let { noteRef.child(it).removeValue() }
    }
}
