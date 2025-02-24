package com.example.myapplication.data.remote.plant

import com.example.myapplication.domain.entities.Plant
import retrofit2.Response
import retrofit2.http.GET

interface PvzDataSource {
    @GET("plants")
    suspend fun getPlants() : Response<List<String>>
}