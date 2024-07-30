package nz.co.plantandfood.mvvmtodoapp.presentation.screen.todo_list

import nz.co.plantandfood.mvvmtodoapp.domain.Todo

sealed class TodoListAction {
    data class OnDeleteTodoClick(val todo: Todo): TodoListAction()
    data class OnDoneChange(val todo: Todo, val isDone: Boolean): TodoListAction()
    data object OnUndoDeleteClick: TodoListAction()
    data class OnTodoClick(val todo: Todo): TodoListAction()
    data object OnAddTodoClick: TodoListAction()
}
