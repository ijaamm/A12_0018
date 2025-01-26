package com.example.finalprjectpam.ui.viewmodel.pendaftaranviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprjectpam.model.Pendaftaran
import com.example.finalprjectpam.repository.PendaftaranRepository
import kotlinx.coroutines.launch

class InsertPViewModel(
    private val pdft: PendaftaranRepository
): ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertPdftState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertPdft(){
        viewModelScope.launch {
            try {
                pdft.insertPendaftaran(uiState.insertUiEvent.toPdft())
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
    val idPendaftaran: String = "",
    val idSiswa: String = "",
    val idKursus: String = "",
    val tanggal: String = ""
)

fun InsertUiEvent.toPdft(): Pendaftaran = Pendaftaran(
    idPendaftaran = idPendaftaran,
    idSiswa = idSiswa,
    idKursus = idKursus,
    tanggal = tanggal
)

fun Pendaftaran.toUiStatePdft(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Pendaftaran.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idPendaftaran = idPendaftaran,
    idSiswa = idSiswa,
    idKursus = idKursus,
    tanggal = tanggal
)