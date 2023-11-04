package com.example.gtn.screens

data class GameUiState(
   // var score: Int = 0,
    var isGameOver: Boolean = false,
    //var userguess: String = "",
    //var message: String = "Guess a number between 0 and 100"
)
// The GameUiState data class is designed to encapsulate all the variable parts of your UI that can change over time. By including all changeable elements in one object, you create a single source of truth for the UI's state. This way, when any element changes, you update and emit a new GameUiState object, and the UI will recompose to reflect these changes