package com.example.myapplication.presentation.pages.plant.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.use_case.plant.GetPlantsUseCase
import com.example.myapplication.presentation.pages.plant.view_model.state.PlantsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlantsViewModel(private val getPlantsUseCase: GetPlantsUseCase) : ViewModel() {
    private val _state = MutableStateFlow<PlantsState>(PlantsState.Loading)
    val state = _state.asStateFlow()

    fun getPlants() {
       _state.value = PlantsState.Loading
        viewModelScope.launch {
            getPlantsUseCase().collect {result ->
                _state.value = result.fold(
                    left = {PlantsState.Error(it)},
                    right = {PlantsState.Success(it)}
                )
            }
        }
    }
}

class PlantsViewModelFactory(private val getPlantsUseCase: GetPlantsUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlantsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlantsViewModel(getPlantsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

