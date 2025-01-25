package com.example.finalprjectpam.di

import com.example.finalprjectpam.repository.NetworkPendaftaranRepository
import com.example.finalprjectpam.repository.PendaftaranRepository
import com.example.finalprjectpam.service.PendaftaranService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface PendaftaranContainer{
    val pendaftaranRepository: PendaftaranRepository
}

class PdftContainer : PendaftaranContainer {

    private val baseUrl = "http://10.0.2.2:3500/api/pendaftaran/"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val pendaftaranService: PendaftaranService by lazy {
        retrofit.create(PendaftaranService::class.java)
    }

    override val pendaftaranRepository: PendaftaranRepository by lazy {
        NetworkPendaftaranRepository(pendaftaranService)
    }
}