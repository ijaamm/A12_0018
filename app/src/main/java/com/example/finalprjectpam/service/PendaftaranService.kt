package com.example.finalprjectpam.service

import com.example.finalprjectpam.model.AllPendaftaranResponse
import com.example.finalprjectpam.model.Pendaftaran
import com.example.finalprjectpam.model.PendaftaranDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PendaftaranService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    @POST("store")
    suspend fun insertPendaftaran( @Body pendaftaran: Pendaftaran)

    @GET(".")
    suspend fun getAllPendaftaran(): AllPendaftaranResponse

    @GET("{id_pendaftaran}")
    suspend fun getPendaftaranById(@Path("id_pendaftaran") idPendaftaran: String): PendaftaranDetailResponse

    @PUT("{id_pendaftaran}")
    suspend fun updatePendaftaran(@Path("id_pendaftaran") idPendaftaran: String, @Body pendaftaran: Pendaftaran)

    @DELETE("{id_pendaftaran}")
    suspend fun deletePendaftaran(@Path("id_pendaftaran") idPendaftaran: String): Response<Void>


}