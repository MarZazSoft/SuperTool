package com.marzazsoft.mobile.notes.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marzazsoft.mobile.notes.models.Note
import com.marzazsoft.mobile.notes.usecases.NotesUseCase
import com.marzazsoft.mobile.supertool.common.utils.ApiStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NotesViewModel(private val notesUseCase: NotesUseCase) : ViewModel() {
    private val _notesState = MutableStateFlow<ApiStatus<List<Note>>>(ApiStatus.Loading)
    val notesState: StateFlow<ApiStatus<List<Note>>> = _notesState.asStateFlow()

    private val _selectedNote = MutableStateFlow<Note?>(null)
    val selectedNote: StateFlow<Note?> = _selectedNote.asStateFlow()

    fun loadNotes() {
        viewModelScope.launch {
            _notesState.value = ApiStatus.Loading
            _notesState.value = ApiStatus.Success(notesUseCase.getAllNotes())
        }
    }

    fun loadNote(id: Int) {
        viewModelScope.launch {
            _selectedNote.value = notesUseCase.getNoteById(id)
        }
    }

    fun saveNote(
        title: String,
        content: String,
    ) {
        viewModelScope.launch {
            notesUseCase.saveNote(Note(title = title, content = content))
        }
    }

    fun updateNote(
        note: Note,
        title: String,
        content: String,
    ) {
        viewModelScope.launch {
            notesUseCase.updateNote(note.copy(title = title, content = content))
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            notesUseCase.deleteNote(note)
        }
    }
}
