package nz.co.plantandfood.mvvmtodoapp.presentation.screen.todo_list

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nz.co.plantandfood.mvvmtodoapp.domain.Todo

@Composable
fun TodoItem(
    todo: Todo,
    onAction: (TodoListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = todo.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = {
                    onAction(TodoListAction.OnDeleteTodoClick(todo))
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }
            }
            todo.description?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it)
            }
        }
        Checkbox(
            checked = todo.isDone,
            onCheckedChange = { isChecked ->
                onAction(TodoListAction.OnDoneChange(todo, isChecked))
            }
        )
    }
}