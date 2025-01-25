package com.example.finalprjectpam.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

    @Serializable
    data class Instruktur(

        @SerialName(value = "id_instruktur")
        val idInstruktur: String,

        @SerialName(value = "nama_instruktur")
        val namaInstruktur: String,

        @SerialName(value = "email_instruktur")
        val emailI: String,

        @SerialName(value = "notelp_instruktur")
        val noTeleponI: String,

        @SerialName(value = "deskripsi_instruktur")
        val deskripsiI: String
    )

    @Serializable
    data class AllInstrukturResponse(
        val status: Boolean,
        val message: String,
        val data: List<Instruktur>
    )

    @Serializable
    data class InstrukturDetailResponse(
        val status: Boolean,
        val message: String,
        val data: Instruktur
    )