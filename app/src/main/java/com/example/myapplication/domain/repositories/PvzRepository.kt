package com.example.myapplication.domain.repositories

import Either
import com.example.myapplication.domain.entities.Plant
import kotlinx.coroutines.flow.Flow

interface PvzRepository {
    suspend fun getPlants(): Flow<Either<String, List<Plant>>>
}