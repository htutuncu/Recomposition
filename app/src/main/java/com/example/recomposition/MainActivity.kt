package com.example.recomposition

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recomposition.ui.theme.RecompositionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecompositionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CounterApp()
                }
            }
        }
    }
}

@Composable
fun CounterApp() {
    val count = remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = "Count: ${count.value}")
        Button(onClick = { count.value++ }) {
            Text(text = "Increase")
        }
        Button(onClick = { count.value-- }) {
            Text(text = "Decrease")
        }
    }
}

@Composable
fun CounterScreenStateHoisting() {
    val countState = remember { mutableStateOf(0) }

    Column {
        Counter(
            count = countState.value,
            onIncrease = {
                countState.value++
            },
            onDecrease = {
                countState.value--
            }
        )
    }
}

@Composable
fun Counter(count: Int, onIncrease: () -> Unit, onDecrease: () -> Unit) {
    Text(text = "Count: $count")
    Button(onClick = onIncrease) {
        Text(text = "Increase")
    }
    Button(onClick = onDecrease) {
        Text(text = "Decrease")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecompositionTheme {
        CounterScreenStateHoisting()
    }
}