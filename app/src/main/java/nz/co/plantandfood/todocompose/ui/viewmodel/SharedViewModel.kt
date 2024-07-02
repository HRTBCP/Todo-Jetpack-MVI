package nz.co.plantandfood.todocompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import nz.co.plantandfood.todocompose.data.models.TodoTask
import nz.co.plantandfood.todocompose.data.repositories.TodoRepository
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {
    private val _allTasks = MutableStateFlow<List<TodoTask>>(emptyList())
    val allTasks = _allTasks

    fun getAllTasks() {
        viewModelScope.launch {
            repository.getAllTasks.collect{
                _allTasks.value = it
            }
        }
    }
}