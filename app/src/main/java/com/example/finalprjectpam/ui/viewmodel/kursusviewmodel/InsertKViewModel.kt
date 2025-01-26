package com.example.finalprjectpam.ui.viewmodel.kursusviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprjectpam.model.Kursus
import com.example.finalprjectpam.repository.KursusRepository
import kotlinx.coroutines.launch

class InsertKViewModel(
    private val krs: KursusRepository,
) : ViewModel() {

    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertKrsState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertKrs(){
        viewModelScope.launch {
            try {
                krs.insertKursus(uiState.insertUiEvent.toKrs())
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val idKursus: String = "",
    val namaKursus: String = "",
    val deskripsiK: String = "",
    val kategori: String = "",
    val harga: String = "",
    val idInstruktur: String = ""
)

fun InsertUiEvent.toKrs(): Kursus = Kursus(
    idKursus = idKursus,
    namaKursus = namaKursus,
    deskripsiK = deskripsiK,
    kategori = kategori,
    harga = harga,
    idInstruktur = idInstruktur
)

fun Kursus.toUiStateKrs(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Kursus.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idKursus = idKursus,
    namaKursus = namaKursus,
    deskripsiK = deskripsiK,
    kategori = kategori,
    harga = harga,
    idInstruktur = idInstruktur
)