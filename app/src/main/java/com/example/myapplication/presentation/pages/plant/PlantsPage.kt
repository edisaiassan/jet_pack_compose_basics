package com.example.myapplication.presentation.pages.plant

import SimpleCard
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.domain.use_case.plant.GetPlantsUseCase
import com.example.myapplication.presentation.pages.plant.view_model.PlantsViewModel
import com.example.myapplication.presentation.pages.plant.view_model.PlantsViewModelFactory
import com.example.myapplication.presentation.pages.plant.view_model.state.PlantsState


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PlantsPage(getPlantsUseCase: GetPlantsUseCase) {
    val viewModel: PlantsViewModel = viewModel(factory = PlantsViewModelFactory(getPlantsUseCase))
    val state by viewModel.state.collectAsState()

    //GetPlants
    LaunchedEffect(Unit) {
        viewModel.getPlants()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Plants") })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues).fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            when(state) {
                is PlantsState.Loading -> {
                    item {
                        CircularProgressIndicator()
                    }
                }
                is PlantsState.Error -> {
                    val message = (state as PlantsState.Error).message
                    item {
                        Text(text = "Error: $message")
                        Button(
                            onClick = {viewModel.getPlants()}
                        ) {
                            Text(text = "Refresh")
                        }
                    }
                }
                is PlantsState.Success -> {
                    val plants = (state as PlantsState.Success).plants
                    items(plants) {plant ->
                        SimpleCard(title = plant.name)
                    }
                }
            }
        }
    }
}