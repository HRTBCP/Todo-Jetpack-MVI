package nz.co.plantandfood.todocompose.ui.screen.list

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nz.co.plantandfood.todocompose.R
import nz.co.plantandfood.todocompose.components.PriorityItem
import nz.co.plantandfood.todocompose.data.models.Priority

@Composable
fun ListAppBar (){
    DefaultListAppBar(
        nSearchClicked = {},
        onSortClicked =  {},
        onclickDeleteAll = {}
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppBar(nSearchClicked: () -> Unit,
                      onSortClicked: (priority: Priority) -> Unit,
                      onclickDeleteAll:() -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.list_screen_title),
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            ListAppBarActions(nSearchClicked,onSortClicked, onclickDeleteAll)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        )

    )

}

@Composable
fun ListAppBarActions(nSearchClicked: () -> Unit,
                      onSortClicked: (priority: Priority) -> Unit,
                        onclickDeleteAll:() -> Unit)
 {
    SearchAction(nSearchClicked)
    SortAction(onSortClicked)
    MoreActions(onclickDeleteAll)
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit
) {
    IconButton(onClick = {
        onSearchClicked()
    }) {
        Icon(imageVector = Icons.Default.Search,
            contentDescription = stringResource(id = R.string.app_bar_search ))
    }

}

@Composable
fun SortAction(
    onSortClicked: (priority: Priority) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = {
        expanded = true
    }) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_filter_list_24),
            contentDescription = stringResource(id = R.string.app_bar_sort_task)
        )
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = {
            expanded = false
        }) {
        DropdownMenuItem(
            text = { PriorityItem(priority = Priority.HIGH)},
            onClick = {
                expanded = false
                onSortClicked(Priority.HIGH)
            }
        )
        DropdownMenuItem(
            text = { PriorityItem(priority = Priority.MEDIUM)},
            onClick = {
                expanded = false
                onSortClicked(Priority.MEDIUM)
            }
        )
        DropdownMenuItem(
            text = { PriorityItem(priority = Priority.LOW)},
            onClick = {
                expanded = false
                onSortClicked(Priority.LOW)
            }
        )


    }
}

@Composable
fun MoreActions(ondeleteClicked: ()->Unit) {
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = {
        expanded = true
    }) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_more_vert_24),
            contentDescription = stringResource(id = R.string.other_option)
        )
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = {
            expanded = false
        }) {

        DropdownMenuItem(
            text = { Text(text = "Delete All")},
            onClick = {
                expanded = false
                ondeleteClicked()
            }
        )



    }
}
@Composable
@Preview
private fun DefaultListAppBarPreview() {
    DefaultListAppBar({}, {}, {})

}