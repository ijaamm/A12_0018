package com.example.finalprjectpam.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pendaftaran(

    @SerialName(value = "id_pendaftaran")
    val idPendaftaran: String,

    @SerialName(value = "id_siswa")
    val idSiswa: String,

    @SerialName(value = "id_kursus")
    val idKursus: String,

    val tanggal: String
)

@Serializable
data class AllPendaftaranResponse(
    val status: Boolean,
    val message: String,
    val data: List<Pendaftaran>
)

@Serializable
data class PendaftaranDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Pendaftaran
)