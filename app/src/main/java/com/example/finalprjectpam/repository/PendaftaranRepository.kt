package com.example.finalprjectpam.repository

import com.example.finalprjectpam.model.AllPendaftaranResponse
import com.example.finalprjectpam.model.Pendaftaran
import com.example.finalprjectpam.service.PendaftaranService
import java.io.IOException

interface PendaftaranRepository{
    suspend fun insertPendaftaran(Pendaftaran: Pendaftaran)

    suspend fun getPendaftaran(): AllPendaftaranResponse

    suspend fun getPendaftaranById(idPendaftaran: String): Pendaftaran

    suspend fun updatePendaftaran(idPendaftaran: String, Pendaftaran: Pendaftaran)

    suspend fun deletePendaftaran(idPendaftaran: String)
}

class NetworkPendaftaranRepository(
    private val pendaftaranApiService: PendaftaranService
): PendaftaranRepository {
    override suspend fun insertPendaftaran(pendaftaran: Pendaftaran) {
        pendaftaranApiService.insertPendaftaran(pendaftaran)
    }

    override suspend fun updatePendaftaran(idPendaftaran: String, pendaftaran: Pendaftaran) {
        pendaftaranApiService.updatePendaftaran(idPendaftaran, pendaftaran)
    }

    override suspend fun getPendaftaran(): AllPendaftaranResponse {
        return pendaftaranApiService.getAllPendaftaran()
    }

    override suspend fun deletePendaftaran(idPendaftaran: String) {
        try {
            val reponse = pendaftaranApiService.deletePendaftaran(idPendaftaran)
            if (!reponse.isSuccessful) {
                throw IOException("Failed to delete kontak. HTTP Status code: " +
                        "${reponse.code()}")
            } else {
                reponse.message()
                println(reponse.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getPendaftaranById(idPendaftaran: String): Pendaftaran {
        return pendaftaranApiService.getPendaftaranById(idPendaftaran).data
    }
}