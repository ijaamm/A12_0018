package com.example.finalprjectpam.di

import com.example.finalprjectpam.repository.InstrukturRepository
import com.example.finalprjectpam.repository.NetworkInstrukturRepository
import com.example.finalprjectpam.service.InstrukturService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface InstrukturContainer {
    val instrukturRepository: InstrukturRepository
}

class InsContainer : InstrukturContainer {

    private val baseUrl = "http://10.0.2.2:3500/api/instruktur/"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val instrukturService: InstrukturService by lazy {
        retrofit.create(InstrukturService::class.java)
    }

    override val instrukturRepository: InstrukturRepository by lazy {
        NetworkInstrukturRepository(instrukturService)
    }
}