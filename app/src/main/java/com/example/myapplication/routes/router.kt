package com.example.myapplication.routes

import LoginPage
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.remote.plant.RetrofitInstance
import com.example.myapplication.data.repositories_implementation.PvzRepositoryImpl
import com.example.myapplication.domain.use_case.plant.GetPlantsUseCase
import com.example.myapplication.presentation.pages.plant.PlantsPage

@Composable
fun NavigationWrapper() {
    val apiService = RetrofitInstance.api  // Instancia del servicio API
    val repository = PvzRepositoryImpl(apiService) // Repositorio con Retrofit
    val getPlantsUseCase = GetPlantsUseCase(repository) // UseCase con el repositorio

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginPage(navController) }
        composable("plant"){ PlantsPage(getPlantsUseCase) }
    }
}