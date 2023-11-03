package com.example.gtn.screens

data class GameUiState(
    val score: Int = 0,
    val isGameOver: Boolean = false,
    //val userInput: String = "",
    //var message: String = ""
)
// The GameUiState data class is designed to encapsulate all the variable parts of your UI that can change over time. By including all changeable elements in one object, you create a single source of truth for the UI's state. This way, when any element changes, you update and emit a new GameUiState object, and the UI will recompose to reflect these changes