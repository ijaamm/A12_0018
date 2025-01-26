package com.example.finalprjectpam.ui.viewmodel.instrukturviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprjectpam.model.Instruktur
import com.example.finalprjectpam.repository.InstrukturRepository
import kotlinx.coroutines.launch

class InsertIViewModel(
    private val ins: InstrukturRepository
) : ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertInsState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertIns(){
        viewModelScope.launch {
            try {
                ins.insertInstruktur(uiState.insertUiEvent.toIns())
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
    val idInstruktur: String = "",
    val namaInstruktur: String = "",
    val emailI: String = "",
    val noTeleponI: String = "",
    val deskripsiI: String = ""
)

fun InsertUiEvent.toIns(): Instruktur = Instruktur(
    idInstruktur = idInstruktur,
    namaInstruktur = namaInstruktur,
    emailI = emailI,
    noTeleponI = noTeleponI,
    deskripsiI = deskripsiI
)

fun Instruktur.toUiStateIns(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Instruktur.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idInstruktur = idInstruktur,
    namaInstruktur = namaInstruktur,
    emailI = emailI,
    noTeleponI = noTeleponI,
    deskripsiI = deskripsiI
)