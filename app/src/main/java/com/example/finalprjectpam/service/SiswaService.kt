package com.example.finalprjectpam.service

import com.example.finalprjectpam.model.AllSiswaResponse
import com.example.finalprjectpam.model.Siswa
import com.example.finalprjectpam.model.SiswaDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface SiswaService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    @POST("store")
    suspend fun insertSiswa( @Body siswa: Siswa)

    @GET(".")
    suspend fun getAllSiswa(): AllSiswaResponse

    @GET("{id_siswa}")
    suspend fun getSiswaById(@Path("id_siswa") idSiswa: String): SiswaDetailResponse

    @PUT("{id_siswa}")
    suspend fun updateSiswa(@Path("id_siswa") idSiswa: String, @Body siswa: Siswa)

    @DELETE("{id_siswa}")
    suspend fun deleteSiswa(@Path("id_siswa") idSiswa: String): Response<Void>

}