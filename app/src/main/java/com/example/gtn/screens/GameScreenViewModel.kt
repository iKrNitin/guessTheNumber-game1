package com.example.gtn.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class GTNViewModel:ViewModel(){
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private fun generateRandomNumber():Int{
        var randomNum = Random.nextInt(0,100)
        return randomNum
    }
    /*fun updateUserGuess1(guessedNum:String){
        GameUiState().userInput = guessedNum
    }*/

    var userGuess by mutableStateOf("")
        private set
    fun updateUserGuess2(guessNum:String){
        userGuess= guessNum
    }

    var randomNumber = generateRandomNumber()
    var message by mutableStateOf("Guess a number between 0 and 100")
    var score by mutableStateOf("0")
   // var gameScore = score.toIntOrNull()
    fun checkUserGuess(){
        var userInput = userGuess.toIntOrNull()

        if (userInput != null)
        {
when{
    userInput < randomNumber -> {message = "Try a greater number";
        score += 1
    }
    userInput > randomNumber ->{
        message = "Try a smaller number";
        score += 1
    }
    else -> {
        /*message = "Hurray! you guessed the correct number which is $randomNumber"*/
        _uiState.value = GameUiState( isGameOver = true)
    }
}
        }
        else{
            message = "Please enter a valid value"
        }
    }

    fun resetGame(){
        userGuess = ""
        message = "Guess a number between 0 and 100"
        score += 1
        _uiState.value = GameUiState( isGameOver = false)
    }

    /*fun updateGameState(updatedScore: Int){
        if (randomNumber == _uiState.value.userguess.toIntOrNull() ){
            _uiState.update {
                currentState -> currentState.copy(
                    isGameOver = true,
                    score = updatedScore,
                )
            }
        }
        else{
            _uiState.update { currentState -> currentState.copy(
                score = updatedScore
            ) }
        }

    }*/

}