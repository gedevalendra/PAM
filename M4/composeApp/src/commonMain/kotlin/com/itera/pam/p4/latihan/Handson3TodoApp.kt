package com.itera.pam.p4.latihan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Latihan 3: Todo App dengan ViewModel — implementasi MVVM pattern (slide P4 hal. 31)
//
// Checklist:
// [ ] Data class Todo
// [ ] TodoUiState dengan list
// [ ] TodoViewModel dengan StateFlow
// [ ] Add todo function
// [ ] Toggle done function
// [ ] TextField untuk input
// [ ] LazyColumn untuk list
// [ ] Checkbox untuk done

// 1. Data class
data class Todo(val id: Int, val text: String, val done: Boolean)

data class TodoUiState(
    val todos: List<Todo> = emptyList(),
    val input: String = ""
)

// 2. ViewModel
class TodoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TodoUiState())
    val uiState: StateFlow<TodoUiState> = _uiState.asStateFlow()

    fun onInputChange(text: String) {
        // TODO: update _uiState.input dengan `text`, gunakan _uiState.update { it.copy(input = text) }
    }

    fun addTodo() {
        // TODO: tambahkan Todo baru ke uiState.todos dari uiState.input (id boleh pakai todos.size),
        //       lalu kosongkan input. Jangan tambahkan jika input blank.
    }

    fun toggleTodo(id: Int) {
        // TODO: toggle `done` untuk Todo dengan id yang cocok
        //       (map list, ganti item yang id-nya sama dengan copy(done = !done))
    }
}

// 3. Compose UI
@Composable
fun Handson3Screen(viewModel: TodoViewModel = viewModel { TodoViewModel() }) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Latihan 3: Todo App dengan ViewModel")
        Text("Belum diimplementasikan — lengkapi TODO di TodoViewModel dan UI di bawah ini")

        // TODO: Implement UI:
        //  - OutlinedTextField untuk input (value = uiState.input, onValueChange = viewModel::onInputChange)
        //  - Button "Tambah" -> viewModel.addTodo()
        //  - LazyColumn menampilkan uiState.todos, tiap item pakai Row + Checkbox(checked = todo.done,
        //    onCheckedChange = { viewModel.toggleTodo(todo.id) }) + Text(todo.text)
    }
}
