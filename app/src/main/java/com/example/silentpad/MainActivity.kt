package com.example.silentpad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.silentpad.data.Note
import com.example.silentpad.data.NoteDatabase
import com.example.silentpad.data.NoteDao
import com.example.silentpad.ui.theme.SilentPadTheme
import com.example.silentpad.ui.theme.Black
import com.example.silentpad.ui.theme.LightBlue
import com.example.silentpad.ui.theme.White

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SilentPadTheme {
                val database = NoteDatabase.getDatabase(this)
                val viewModel = viewModel<NoteViewModel>(
                    factory = object : ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            @Suppress("UNCHECKED_CAST")
                            return NoteViewModel(database.noteDao()) as T
                        }
                    }
                )
                MainScreen(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: NoteViewModel) {
    val notes by viewModel.notes.collectAsState(initial = emptyList())
    var showAddDialog by remember { mutableStateOf(false) }
    var selectedNote by remember { mutableStateOf<Note?>(null) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "SilentPad",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Black
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    selectedNote = null
                    showAddDialog = true
                },
                containerColor = LightBlue,
                contentColor = Black
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        },
        containerColor = Black
    ) { paddingValues ->
        if (notes.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No notes yet.\nTap + to create one!",
                    fontSize = 16.sp,
                    color = White.copy(alpha = 0.7f),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(notes) { note ->
                    NoteItem(
                        note = note,
                        onNoteClick = {
                            selectedNote = note
                            showAddDialog = true
                        },
                        onDeleteClick = {
                            viewModel.deleteNote(note)
                        }
                    )
                }
            }
        }
        
        if (showAddDialog) {
            AddEditNoteDialog(
                note = selectedNote,
                onDismiss = { showAddDialog = false },
                onSave = { title, content ->
                    if (selectedNote != null) {
                        viewModel.updateNote(selectedNote!!.copy(
                            title = title,
                            content = content,
                            updatedAt = System.currentTimeMillis()
                        ))
                    } else {
                        viewModel.addNote(title, content)
                    }
                    showAddDialog = false
                }
            )
        }
    }
}

@Composable
fun NoteItem(
    note: Note,
    onNoteClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onNoteClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightBlue.copy(alpha = 0.2f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = note.title.ifEmpty { "Untitled" },
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = note.content,
                    fontSize = 14.sp,
                    color = White.copy(alpha = 0.8f),
                    maxLines = 3
                )
            }
            IconButton(onClick = onDeleteClick) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = White
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteDialog(
    note: Note?,
    onDismiss: () -> Unit,
    onSave: (String, String) -> Unit
) {
    var title by remember { mutableStateOf(note?.title ?: "") }
    var content by remember { mutableStateOf(note?.content ?: "") }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = if (note == null) "New Note" else "Edit Note",
                color = White
            )
        },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title", color = White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = White,
                        unfocusedTextColor = White,
                        focusedBorderColor = LightBlue,
                        unfocusedBorderColor = LightBlue,
                        focusedLabelColor = LightBlue,
                        unfocusedLabelColor = LightBlue
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    label = { Text("Content", color = White) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    maxLines = 10,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = White,
                        unfocusedTextColor = White,
                        focusedBorderColor = LightBlue,
                        unfocusedBorderColor = LightBlue,
                        focusedLabelColor = LightBlue,
                        unfocusedLabelColor = LightBlue
                    )
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onSave(title, content) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = LightBlue,
                    contentColor = Black
                )
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = White)
            }
        },
        containerColor = Black
    )
}

class NoteViewModel(private val noteDao: NoteDao) : ViewModel() {
    val notes: Flow<List<Note>> = noteDao.getAllNotes()
    
    fun addNote(title: String, content: String) {
        viewModelScope.launch {
            noteDao.insertNote(
                Note(
                    title = title,
                    content = content
                )
            )
        }
    }
    
    fun updateNote(note: Note) {
        viewModelScope.launch {
            noteDao.updateNote(note)
        }
    }
    
    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteDao.deleteNote(note)
        }
    }
}

