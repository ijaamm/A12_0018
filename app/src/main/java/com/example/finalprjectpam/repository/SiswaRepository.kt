package com.example.finalprjectpam.repository

import com.example.finalprjectpam.model.AllSiswaResponse
import com.example.finalprjectpam.model.Siswa
import com.example.finalprjectpam.service.SiswaService
import okio.IOException

interface SiswaRepository {
    suspend fun insertSiswa(Siswa: Siswa)

    suspend fun getSiswa(): AllSiswaResponse

    suspend fun updateSiswa(idSiswa: String, Siswa: Siswa)

    suspend fun deleteSiswa(idSiswa: String)

    suspend fun getSiswaById(idSiswa: String): Siswa
}

class NetworkSiswaRepository(
    private val siswaApiService: SiswaService
) : SiswaRepository{
    override suspend fun insertSiswa(Siswa: Siswa) {
        siswaApiService.insertSiswa(Siswa)
    }

    override suspend fun getSiswa(): AllSiswaResponse {
        return siswaApiService.getAllSiswa()
    }

    override suspend fun updateSiswa(idSiswa: String, Siswa: Siswa) {
        siswaApiService.updateSiswa(idSiswa, Siswa)
    }

    override suspend fun deleteSiswa(idSiswa: String) {
        try{
            val response = siswaApiService.deleteSiswa(idSiswa)
            if (!response.isSuccessful){
                throw IOException("Failed to delete kontak. HTTP Status code: " +
                        "${response.code()}")
            }else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception){
            throw e
        }
    }

    override suspend fun getSiswaById(idSiswa: String): Siswa {
        return siswaApiService.getSiswaById(idSiswa).data
    }
}