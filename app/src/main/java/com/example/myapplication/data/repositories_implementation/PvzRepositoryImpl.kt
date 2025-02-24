package com.example.myapplication.data.repositories_implementation

import com.example.myapplication.data.remote.plant.PvzDataSource
import com.example.myapplication.domain.entities.Plant
import com.example.myapplication.domain.repositories.PvzRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PvzRepositoryImpl(private val apiService: PvzDataSource) : PvzRepository {
    override suspend fun getPlants(): Flow<Result<List<Plant>>> = flow {
        emit(Result.success(apiService.getPlants()))
    }.catch { e->
        emit(Result.failure(e))
    }
}