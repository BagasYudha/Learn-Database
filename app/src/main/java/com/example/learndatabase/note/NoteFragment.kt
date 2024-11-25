package com.example.learndatabase.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learndatabase.databinding.FragmentNoteBinding

class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private val noteRepository = NoteRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val noteAdapter = NoteAdapter(mutableListOf(),
            onEdit = { note ->
                binding.etNote.setText(note.name)
                binding.btnAdd.setOnClickListener {
                    val updatedName = binding.etNote.text.toString()
                    noteRepository.updateNote(Note(note.id, updatedName))
                }
            },
            onDelete = { note ->
                noteRepository.deleteNote(note)
            }
        )

        binding.rvNotes.adapter = noteAdapter
        binding.rvNotes.layoutManager = LinearLayoutManager(context)

        binding.btnAdd.setOnClickListener {
            val noteName = binding.etNote.text.toString()
            if (noteName.isNotEmpty()) {
                val newNote = Note(name = noteName)
                noteRepository.addNote(newNote)
                binding.etNote.text.clear()
            }
        }

        // Observe notes data from repository
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            noteRepository.notes.collect { notes ->
                noteAdapter.updateNotes(notes)
            }
        }
    }
}
