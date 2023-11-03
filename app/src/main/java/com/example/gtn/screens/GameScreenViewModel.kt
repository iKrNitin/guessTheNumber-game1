package com.example.gtn.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
        userGuess = guessNum
    }

    var randomNumber = generateRandomNumber()
    var message by mutableStateOf("Guess a number between 0 and 100")
    fun checkUserGuess(){
        var userInput = userGuess.toIntOrNull()

        if (userInput != null)
        {
when{
    userInput < randomNumber -> {message = "Try a greater number"}
    userInput > randomNumber ->{
        message = "Try a smaller number"
    }
    else -> {
        message = "Hurray! you guessed the correct number which is $randomNumber"
    }
}
        }
        else{
            message = "Please enter a valid value"
        }
    }
}