package nz.co.plantandfood.mvvmtodoapp.presentation.util

import androidx.compose.material3.SnackbarDuration

sealed class UiEffect {
    object PopBackStack: UiEffect()
    data class Navigate(val route: Routes.TodoEdit): UiEffect()
    data class ShowSnackbar(
        val message: String,
        val action: String? = null,
        var duration: SnackbarDuration = SnackbarDuration.Short
    ): UiEffect()
}
