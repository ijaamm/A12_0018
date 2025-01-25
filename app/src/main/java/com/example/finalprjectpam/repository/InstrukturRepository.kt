package com.example.finalprjectpam.repository

import com.example.finalprjectpam.model.AllInstrukturResponse
import com.example.finalprjectpam.model.Instruktur
import com.example.finalprjectpam.service.InstrukturService
import okio.IOException

interface InstrukturRepository{
    suspend fun insertInstruktur(Instruktur: Instruktur)

    suspend fun getInstruktur(): AllInstrukturResponse

    suspend fun updateInstruktur(idInstruktur: String, Instruktur: Instruktur)

    suspend fun deleteInstruktur(idInstruktur: String)

    suspend fun getInstrukturById(idInstruktur: String): Instruktur

}

class NetworkInstrukturRepository(
    private val instrukturApiService: InstrukturService
): InstrukturRepository{
    override suspend fun insertInstruktur(Instruktur: Instruktur) {
        instrukturApiService.insertInstruktur(Instruktur)
    }

    override suspend fun getInstruktur(): AllInstrukturResponse {
        return instrukturApiService.getAllInstruktur()
    }

    override suspend fun updateInstruktur(idInstruktur: String, Instruktur: Instruktur) {
        instrukturApiService.updateInstruktur(idInstruktur, Instruktur)
    }

    override suspend fun deleteInstruktur(idInstruktur: String) {
        try{
            val response = instrukturApiService.deleteInstruktur(idInstruktur)
            if (!response.isSuccessful){
                throw IOException("Failed to delete kontak. HTTP Status code: " +
                        "${response.code()}")
            }else{
                response.message()
                println(response.message())
            }
        }catch (e: Exception){
            throw e
        }
    }

    override suspend fun getInstrukturById(idInstruktur: String): Instruktur {
        return instrukturApiService.getInstrukturById(idInstruktur).data
    }
}