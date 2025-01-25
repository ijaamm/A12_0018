package com.example.finalprjectpam.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Siswa(

    @SerialName(value = "id_siswa")
    val idSiswa: String,

    @SerialName(value = "nama_siswa")
    val namaSiswa: String,

    @SerialName(value = "email_siswa")
    val emailS: String,

    @SerialName(value = "notelp_siswa")
    val noTeleponS: String
)

@Serializable
data class AllSiswaResponse(
    val status: Boolean,
    val message: String,
    val data: List<Siswa>
)

@Serializable
data class SiswaDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Siswa
)