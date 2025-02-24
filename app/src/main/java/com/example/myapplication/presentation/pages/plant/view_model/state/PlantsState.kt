package com.example.myapplication.presentation.pages.plant.view_model.state
import com.example.myapplication.domain.entities.Plant

sealed class PlantsState {
    data object Loading : PlantsState()
    data class Error(val message: String) : PlantsState()
    data class Success(val plants: List<Plant>) : PlantsState()
}