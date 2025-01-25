package com.example.finalprjectpam.service

import com.example.finalprjectpam.model.AllKursusResponse
import com.example.finalprjectpam.model.Kursus
import com.example.finalprjectpam.model.KursusDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface KursusService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    @POST("store")
    suspend fun insertKursus( @Body kursus: Kursus)

    @GET(".")
    suspend fun getAllKursus(): AllKursusResponse

    @GET("{id_kursus}")
    suspend fun getKursusById(@Path("id_kursus") idKursus: String): KursusDetailResponse

    @PUT("{id_kursus}")
    suspend fun updateKursus(@Path("id_kursus") idKursus: String, @Body kursus: Kursus)

    @DELETE("{id_kursus}")
    suspend fun deleteKursus(@Path("id_kursus") idKursus: String): Response<Void>

}