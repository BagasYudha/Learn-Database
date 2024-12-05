package com.example.learndatabase.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learndatabase.databinding.FragmentNoteBinding
import com.example.learndatabase.setup.AppViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private lateinit var appViewModel: AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi ViewModel
        appViewModel = ViewModelProvider(this).get(AppViewModel::class.java)

        val noteAdapter = NoteAdapter(mutableListOf(),
            onEdit = { note ->
                binding.etNote.setText(note.name)
                binding.btnAdd.setOnClickListener {
                    val updatedName = binding.etNote.text.toString()
                    appViewModel.updateNoteVm(Note(note.id, updatedName))
                }
            },
            onDelete = { note ->
                appViewModel.deleteNoteVm(note)
            }
        )

        // Setup RecyclerView
        binding.rvNotes.adapter = noteAdapter
        binding.rvNotes.layoutManager = LinearLayoutManager(context)

        // Tambah Note
        binding.btnAdd.setOnClickListener {
            val noteName = binding.etNote.text.toString()
            if (noteName.isNotEmpty()) {
                val newNote = Note(name = noteName)
                appViewModel.addNoteVm(newNote)
                binding.etNote.text.clear()
            }
        }

        // Observasi data Note dari ViewModel
        viewLifecycleOwner.lifecycleScope.launch {
            appViewModel.notes.collectLatest { notes ->
                noteAdapter.updateNotes(notes)
            }
        }
    }
}
