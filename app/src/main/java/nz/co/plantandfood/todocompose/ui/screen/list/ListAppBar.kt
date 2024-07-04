package nz.co.plantandfood.todocompose.ui.screen.list

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nz.co.plantandfood.todocompose.R

@Composable
fun ListAppBar (){
    DefaultListAppBar()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.list_screen_title),
                style = MaterialTheme.typography.titleLarge
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        )

    )

}

@Composable
@Preview
private fun DefaultListAppBarPreview() {
    DefaultListAppBar()

}