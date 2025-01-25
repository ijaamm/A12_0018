package com.example.finalprjectpam.service

import com.example.finalprjectpam.model.AllInstrukturResponse
import com.example.finalprjectpam.model.Instruktur
import com.example.finalprjectpam.model.InstrukturDetailResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface InstrukturService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    @POST("store")
    suspend fun insertInstruktur( @Body instruktur: Instruktur)

    @GET(".")
    suspend fun getAllInstruktur(): AllInstrukturResponse

    @GET("{id_instruktur}")
    suspend fun getInstrukturById(@Path("id_instruktur") idInstruktur: String): InstrukturDetailResponse

    @PUT("{id_instruktur}")
    suspend fun updateInstruktur(@Path("id_instruktur") idInstruktur: String, @Body instruktur: Instruktur)

    @DELETE("{id_instruktur}")
    suspend fun deleteInstruktur(@Path("id_instruktur") idInstruktur: String): Response<Void>

}