package nz.co.plantandfood.mvvmtodoapp.presentation.util

sealed class UiEvent {
    object PopBackStack: UiEvent()
    data class Navigate(val route: Routes.TodoEdit): UiEvent()
    data class ShowSnackbar(
        val message: String,
        val action: String? = null
    ): UiEvent()
}
