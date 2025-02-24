package com.example.myapplication

import HomePage
import LoginPage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.data.remote.plant.RetrofitInstance
import com.example.myapplication.data.repositories_implementation.PvzRepositoryImpl
import com.example.myapplication.domain.use_case.plant.GetPlantsUseCase
import com.example.myapplication.presentation.pages.plant.PlantsPage
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = RetrofitInstance.api  // Instancia del servicio API
        val repository = PvzRepositoryImpl(apiService) // Repositorio con Retrofit
        val getPlantsUseCase = GetPlantsUseCase(repository) // UseCase con el repositorio

        enableEdgeToEdge()
        setContent {
            PlantsPage(getPlantsUseCase)
            //HomePage()
            /* MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            } */
        }
    }
}

/* @Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
} */