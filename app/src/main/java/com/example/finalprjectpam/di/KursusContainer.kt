package com.example.finalprjectpam.di

import com.example.finalprjectpam.repository.KursusRepository
import com.example.finalprjectpam.repository.NetworkKursusRepository
import com.example.finalprjectpam.service.KursusService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface KursusContainer{
    val kursusRepository: KursusRepository
}

class KrsContainer : KursusContainer {

    private val baseUrl = "http://10.0.2.2:3500/api/kursus/"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val kursusService: KursusService by lazy {
        retrofit.create(KursusService::class.java)
    }

    override val kursusRepository: KursusRepository by lazy {
        NetworkKursusRepository(kursusService)
    }
}