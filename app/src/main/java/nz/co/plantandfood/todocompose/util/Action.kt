package nz.co.plantandfood.todocompose.util

import kotlinx.serialization.Serializable

@Serializable
enum class Action {
    ADD,
    UPDATE,
    DELETE,
    DELETE_ALL,
    UNDO,
    NO_ACTION
}