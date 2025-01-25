package com.example.finalprjectpam.di

import com.example.finalprjectpam.repository.NetworkSiswaRepository
import com.example.finalprjectpam.repository.SiswaRepository
import com.example.finalprjectpam.service.SiswaService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface SiswaContainer{
    val siswaRepository: SiswaRepository
}

class SswContainer : SiswaContainer {

    private val baseUrl = "http://10.0.2.2:3500/api/siswa/"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val siswaService: SiswaService by lazy {
        retrofit.create(SiswaService::class.java)
    }

    override val siswaRepository: SiswaRepository by lazy {
        NetworkSiswaRepository(siswaService)
    }
}