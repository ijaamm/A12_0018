package com.example.finalprjectpam.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Kursus(

    @SerialName(value = "id_kursus")
    val idKursus: String,

    @SerialName(value = "nama_kursus")
    val namaKursus: String,

    @SerialName(value = "deskripsi_kursus")
    val deskripsiK: String,

    val kategori: String,
    val harga: String,

    @SerialName(value = "id_instruktur")
    val idInstruktur: String
)

@Serializable
data class AllKursusResponse(
    val status: Boolean,
    val message: String,
    val data: List<Kursus>
)

@Serializable
data class KursusDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Kursus
)