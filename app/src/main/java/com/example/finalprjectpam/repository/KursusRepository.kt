package com.example.finalprjectpam.repository

import com.example.finalprjectpam.model.AllKursusResponse
import com.example.finalprjectpam.model.Kursus
import com.example.finalprjectpam.service.KursusService
import okio.IOException

interface KursusRepository {
    suspend fun insertKursus(kursus: Kursus)

    suspend fun getKursus(): AllKursusResponse

    suspend fun updateKursus(idKursus: String, kursus: Kursus)

    suspend fun deleteKursus(idKursus: String)

    suspend fun getKursusById(idKursus: String): Kursus
}

class NetworkKursusRepository(
    private val kursusApiService: KursusService
): KursusRepository{
    override suspend fun insertKursus(kursus: Kursus) {
        kursusApiService.insertKursus(kursus)
    }

    override suspend fun getKursus(): AllKursusResponse {
        return kursusApiService.getAllKursus()
    }

    override suspend fun updateKursus(idKursus: String, kursus: Kursus) {
        kursusApiService.updateKursus(idKursus, kursus)
    }

    override suspend fun deleteKursus(idKursus: String) {
        try{
            val response = kursusApiService.deleteKursus(idKursus)
            if (!response.isSuccessful){
                throw IOException("Failed to delete kontak. HTTP Status code: " +
                        "${response.code()}")
            }else {
                response.message()
                println(response.message())
            }
        }catch (e: Exception){
            throw e
        }
    }

    override suspend fun getKursusById(idKursus: String): Kursus {
        return kursusApiService.getKursusById(idKursus).data
    }
}