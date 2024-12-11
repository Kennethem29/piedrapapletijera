package com.example.piedrapapletijera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.piedrapapletijera.ui.theme.PiedrapapletijeraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PiedrapapletijeraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GameScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    var userChoice by remember { mutableStateOf("") }
    var computerChoice by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Tu elección: $userChoice")
        Text(text = "Elección de la computadora: $computerChoice")
        Text(text = "Resultado: $result")

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Button(onClick = {
                userChoice = "Piedra"
                computerChoice = getComputerChoice()
                result = determineWinner(userChoice, computerChoice)
            }) {
                Text("Piedra")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                userChoice = "Papel"
                computerChoice = getComputerChoice()
                result = determineWinner(userChoice, computerChoice)
            }) {
                Text("Papel")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                userChoice = "Tijera"
                computerChoice = getComputerChoice()
                result = determineWinner(userChoice, computerChoice)
            }) {
                Text("Tijera")
            }
        }
    }
}

fun getComputerChoice(): String {
    val choices = listOf("Piedra", "Papel", "Tijera")
    return choices.random()
}

fun determineWinner(userChoice: String, computerChoice: String): String {
    return when {
        userChoice == computerChoice -> "Empate"
        (userChoice == "Piedra" && computerChoice == "Tijera") ||
                (userChoice == "Papel" && computerChoice == "Piedra") ||
                (userChoice == "Tijera" && computerChoice == "Papel") -> "Ganaste"
        else -> "Perdiste"
    }
}