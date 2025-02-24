package com.example.myapplication.domain.use_case.plant

import Either
import com.example.myapplication.domain.entities.Plant
import com.example.myapplication.domain.repositories.PvzRepository
import kotlinx.coroutines.flow.Flow

class GetPlantsUseCase (private val repository: PvzRepository) {
    suspend operator fun invoke() : Flow<Either<String, List<Plant>>> {
        return repository.getPlants()
    }
}