package com.example.learndatabase.note

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learndatabase.databinding.FragmentNoteBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = Firebase.database
        val noteRef = database.getReference("notes")

        val noteAdapter = NoteAdapter(mutableListOf(),
            onEdit = { note ->
                binding.etNote.setText(note.name)
                binding.btnAdd.setOnClickListener {
                    val updatedName = binding.etNote.text.toString()
                    noteRef.child(note.id!!).setValue(Note(note.id, updatedName))
                }
            },
            onDelete = { note ->
                noteRef.child(note.id!!).removeValue()
            }
        )

        binding.rvNotes.adapter = noteAdapter
        binding.rvNotes.layoutManager = LinearLayoutManager(context)

        binding.btnAdd.setOnClickListener {
            val noteName = binding.etNote.text.toString()
            if (noteName.isNotEmpty()) {
                val noteId = noteRef.push().key
                val newNote = Note(noteId, noteName)
                noteRef.child(noteId!!).setValue(newNote)
                binding.etNote.text.clear()
            }
        }

        noteRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val notes = snapshot.children.mapNotNull { it.getValue(Note::class.java) }
                noteAdapter.updateNotes(notes)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("NoteFragment", "Failed to read notes", error.toException())
            }
        })
    }

}