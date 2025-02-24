package com.example.myapplication.data.repositories_implementation

import Either
import com.example.myapplication.data.remote.plant.PvzDataSource
import com.example.myapplication.domain.entities.Plant
import com.example.myapplication.domain.repositories.PvzRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/*class PvzRepositoryImpl(private val apiService: PvzDataSource) : PvzRepository {
    override suspend fun getPlants(): Flow<Result<List<Plant>>> = flow {
        emit(Result.success(apiService.getPlants()))
    }.catch { e->
        emit(Result.failure(e))
    }
} */

class PvzRepositoryImpl(private val apiService: PvzDataSource) : PvzRepository {
    override suspend fun getPlants(): Flow<Either<String, List<Plant>>> = flow {
        try {
            val response = apiService.getPlants()
            if (response.isSuccessful) {
                val names = response.body() ?: emptyList()
                val plants = names.map { name ->
                    Plant(
                        name = name,
                        image = null,
                        cost = null,
                        recharge = null,
                        family = null,
                        description = null
                    )
                }
                emit(Either.Right(plants))
            } else {
                emit(Either.Left("Error en la API: ${response.code()}"))
            }
        } catch (e: Exception) {
            emit(Either.Left("Error: ${e.message}"))
        }
    }
}

