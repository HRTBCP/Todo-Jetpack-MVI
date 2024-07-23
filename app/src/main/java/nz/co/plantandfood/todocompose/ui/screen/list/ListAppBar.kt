package nz.co.plantandfood.todocompose.ui.screen.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import nz.co.plantandfood.todocompose.R
import nz.co.plantandfood.todocompose.components.PriorityItem
import nz.co.plantandfood.todocompose.data.models.Priority
import nz.co.plantandfood.todocompose.ui.theme.TOP_APP_BAR_HEIGHT
import nz.co.plantandfood.todocompose.ui.viewmodel.SharedViewModel
import nz.co.plantandfood.todocompose.util.SearchAppBarState
import nz.co.plantandfood.todocompose.util.TrailingIconState

@Composable
fun ListAppBar(
    sharedViewModel: SharedViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState: String
) {
    var text by remember {
        mutableStateOf("")
    }
    when (searchAppBarState) {

        SearchAppBarState.CLOSED -> {
            DefaultListAppBar(
                nSearchClicked = {
                    sharedViewModel.searchAppBarState.value = SearchAppBarState.OPENED
                },
                onSortClicked = {},
                onclickDeleteAll = {}
            )
        }

        else -> {

            SearchAppBar(text = searchTextState,
                onTextChange = { sharedViewModel.searchTextState.value = it },
                onClosedClicked = {
                    sharedViewModel.searchAppBarState.value = SearchAppBarState.CLOSED
                    sharedViewModel.searchTextState.value = ""
                }) {

            }
        }

    }
//    DefaultListAppBar(
//        nSearchClicked = {},
//        onSortClicked = {},
//        onclickDeleteAll = {}
//    )


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppBar(
    nSearchClicked: () -> Unit,
    onSortClicked: (priority: Priority) -> Unit,
    onclickDeleteAll: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.list_screen_title),
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            ListAppBarActions(nSearchClicked, onSortClicked, onclickDeleteAll)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        )

    )

}

@Composable
fun ListAppBarActions(
    nSearchClicked: () -> Unit,
    onSortClicked: (priority: Priority) -> Unit,
    onclickDeleteAll: () -> Unit
) {
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
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = stringResource(id = R.string.app_bar_search)
        )
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
            text = { PriorityItem(priority = Priority.HIGH) },
            onClick = {
                expanded = false
                onSortClicked(Priority.HIGH)
            }
        )
        DropdownMenuItem(
            text = { PriorityItem(priority = Priority.MEDIUM) },
            onClick = {
                expanded = false
                onSortClicked(Priority.MEDIUM)
            }
        )
        DropdownMenuItem(
            text = { PriorityItem(priority = Priority.LOW) },
            onClick = {
                expanded = false
                onSortClicked(Priority.LOW)
            }
        )


    }
}

@Composable
fun MoreActions(ondeleteClicked: () -> Unit) {
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
            text = { Text(text = "Delete All") },
            onClick = {
                expanded = false
                ondeleteClicked()
            }
        )


    }
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onClosedClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    var trailingIconState by remember { mutableStateOf<TrailingIconState>(TrailingIconState.READY_TO_CLOSE) }
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(TOP_APP_BAR_HEIGHT),
        color = MaterialTheme.colorScheme.primaryContainer,
        //  elevation = TopAppBarDefaults.TopAppBarElevation,
        onClick = { /*TODO*/ }) {
        TextField(
            modifier = Modifier.fillMaxSize(),
            value = text,
            onValueChange = {
                onTextChange(it)
                trailingIconState = TrailingIconState.READY_TO_DELETE
            },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(alpha = 0.5f),
                    text = "Search"
                )
            },
            textStyle = MaterialTheme.typography.titleSmall,
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {

                    Icon(
                        modifier = Modifier.alpha(alpha = 0.5f),
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.app_bar_search)
                    )
                }
            }, trailingIcon = {
                IconButton(onClick = {
                    when (trailingIconState){
                        TrailingIconState.READY_TO_DELETE ->  {
                            onTextChange("")
                            trailingIconState = TrailingIconState.READY_TO_CLOSE
                        }
                        TrailingIconState.READY_TO_CLOSE -> {
                            onClosedClicked()
                        }
                    }

                }) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Clear")
                }
            }, keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ), keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ), colors = TextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.primaryContainer,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            )
        )
    }

}

@Composable
@Preview
private fun DefaultListAppBarPreview() {
    DefaultListAppBar({}, {}, {})

}


@Composable
@Preview
private fun SearchAppBarPreview() {
    SearchAppBar("Search", {}, {}, {})

}