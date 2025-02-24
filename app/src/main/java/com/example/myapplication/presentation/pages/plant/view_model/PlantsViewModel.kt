package com.example.myapplication.presentation.pages.plant.view_model

import androidx.lifecycle.ViewModel
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
        viewModelScope.launch {
            getPlantsUseCase().collect { result ->
                _state.value = result.fold(
                    onSuccess = {PlantsState.Success(it)},
                    onFailure = {PlantsState.Error(it.localizedMessage ?: "Uknow Error")}
                )
            }
        }
    }
}