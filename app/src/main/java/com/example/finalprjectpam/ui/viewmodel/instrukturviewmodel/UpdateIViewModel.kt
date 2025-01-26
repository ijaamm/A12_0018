package com.example.finalprjectpam.ui.viewmodel.instrukturviewmodel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalprjectpam.model.Instruktur
import com.example.finalprjectpam.repository.InstrukturRepository
import kotlinx.coroutines.launch
import okio.IOException

class UpdateIViewModel(
    private val ins: InstrukturRepository
) : ViewModel() {
    var UpdateuiState by mutableStateOf(UpdateUiState())
        private set

    fun updateState(updateUiEvent: UpdateUiEvent) {
        UpdateuiState = UpdateUiState(updateUiEvent = updateUiEvent)
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun loadInstuktur(idInstruktur: String) {
        viewModelScope.launch {
            try {
                val instruktur = ins.getInstrukturById(idInstruktur)
                UpdateuiState = instruktur.toUpdateUiState()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: HttpException) {
                e.printStackTrace()
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun updateInstruktur() {
        viewModelScope.launch {
            try {
                val instruktur = UpdateuiState.updateUiEvent.toIns()
                ins.updateInstruktur(instruktur.idInstruktur, instruktur)
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: HttpException) {
                e.printStackTrace()
            }
        }
    }
}

data class UpdateUiState(
    val updateUiEvent: UpdateUiEvent = UpdateUiEvent()
)

data class UpdateUiEvent(
    val idInstruktur: String = "",
    val namaInstruktur: String = "",
    val emailI: String = "",
    val noTeleponI: String = "",
    val deskripsiI: String = ""
)

fun UpdateUiEvent.toIns(): Instruktur = Instruktur(
    idInstruktur = idInstruktur,
    namaInstruktur = namaInstruktur,
    emailI = emailI,
    noTeleponI = noTeleponI,
    deskripsiI = deskripsiI
)

fun Instruktur.toUpdateUiState(): UpdateUiState = UpdateUiState(
    updateUiEvent = toUpdateUiEvent()
)

fun Instruktur.toUpdateUiEvent(): UpdateUiEvent = UpdateUiEvent(
    idInstruktur = idInstruktur,
    namaInstruktur = namaInstruktur,
    emailI = emailI,
    noTeleponI = noTeleponI,
    deskripsiI = deskripsiI
)