package nz.co.plantandfood.mvvmtodoapp.presentation.base

import androidx.lifecycle.ViewModel

interface ViewEvent
interface  ViewState
interface ViewSideEffect
abstract class BaseViewModel<Event: ViewEvent, UiState: ViewState, Effect: ViewSideEffect>:ViewModel() {
}