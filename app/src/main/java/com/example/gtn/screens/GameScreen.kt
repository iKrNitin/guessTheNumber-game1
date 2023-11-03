package com.example.gtn.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gtn.R
import com.example.gtn.ui.theme.GTNTheme

@Composable
fun GameScreen(gameViewModel:GTNViewModel){
    val gameUiState by gameViewModel.uiState.collectAsState()

    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        //1st element
        Text(text = stringResource(id = R.string.app_name))
        //2nd element
        GameLayout(message = gameViewModel.message,
            userGuess = gameViewModel.userGuess,
            onUserGuessChanged = {gameViewModel.updateUserGuess2(it)},
            onKeyboardDone = {gameViewModel.checkUserGuess()})
            //3rd element
        Button(modifier = Modifier.fillMaxWidth(),
            onClick = {gameViewModel.checkUserGuess()}) {
            Text(text = stringResource(id = R.string.submit))
        }
//4th element
        GameScore(score = 1,
            modifier = Modifier.padding(20.dp))

    }
}

@Composable
fun GameScore(score:Int,
              modifier: Modifier = Modifier){
    Card(modifier = modifier) {
        Text(text = stringResource(id = R.string.score),
            style = typography.headlineMedium,
            modifier = Modifier.padding(8.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameLayout(
    message:String,
    userGuess:String,
    onUserGuessChanged: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text = message,
                style = typography.displaySmall
            )

            OutlinedTextField(
                value = userGuess,
                singleLine = true,
                shape = shapes.large,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {newValue -> onUserGuessChanged(newValue.filter { it.isDigit() })},
                label = { Text(text = stringResource(id = R.string.enter_number))},
                keyboardActions = KeyboardActions(onDone = {onKeyboardDone()})
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GameScreenPreview(){
GTNTheme {
GameScreen(gameViewModel = GTNViewModel())
}
}