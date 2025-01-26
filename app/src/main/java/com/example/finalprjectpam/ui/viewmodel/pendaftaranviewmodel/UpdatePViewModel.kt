package com.example.finalprjectpam.ui.viewmodel.pendaftaranviewmodel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalprjectpam.model.Pendaftaran
import com.example.finalprjectpam.repository.PendaftaranRepository
import kotlinx.coroutines.launch
import java.io.IOException

class UpdatePViewModel(
    private val pdft: PendaftaranRepository
):  ViewModel() {
    var UpdateuiState by mutableStateOf(UpdateUiState())
        private set

    fun updateState(updateUiEvent: UpdateUiEvent) {
        UpdateuiState = UpdateUiState(updateUiEvent = updateUiEvent)
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun loadPendaftaran(idPendaftaran: String) {
        viewModelScope.launch {
            try {
                val pendaftaran = pdft.getPendaftaranById(idPendaftaran)
                UpdateuiState = pendaftaran.toUpdateUiState()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: HttpException) {
                e.printStackTrace()
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun updatePdft() {
        viewModelScope.launch {
            try {
                val pendaftar = UpdateuiState.updateUiEvent.toPdft()
                pdft.updatePendaftaran(pendaftar.idPendaftaran, pendaftar)
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
    val idPendaftaran: String = "",
    val idSiswa: String = "",
    val idKursus: String = "",
    val tanggal: String = ""
)

fun UpdateUiEvent.toPdft(): Pendaftaran = Pendaftaran(
    idPendaftaran = idPendaftaran,
    idSiswa = idSiswa,
    idKursus = idKursus,
    tanggal = tanggal
)

fun Pendaftaran.toUpdateUiState(): UpdateUiState = UpdateUiState(
    updateUiEvent = toUpdateUiEvent()
)

fun Pendaftaran.toUpdateUiEvent(): UpdateUiEvent = UpdateUiEvent(
    idPendaftaran = idPendaftaran,
    idSiswa = idSiswa,
    idKursus = idKursus,
    tanggal = tanggal
)