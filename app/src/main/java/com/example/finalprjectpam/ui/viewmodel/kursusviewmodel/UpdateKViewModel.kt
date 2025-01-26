package com.example.finalprjectpam.ui.viewmodel.kursusviewmodel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalprjectpam.model.Kursus
import com.example.finalprjectpam.repository.KursusRepository
import kotlinx.coroutines.launch
import java.io.IOException

class UpdateKViewModel(
    private val krs: KursusRepository
): ViewModel() {
    var UpdateuiState by mutableStateOf(UpdateUiState())
        private set

    fun updateState(updateUiEvent: UpdateUiEvent) {
        UpdateuiState = UpdateUiState(updateUiEvent = updateUiEvent)
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun loadKursus(idKursus: String) {
        viewModelScope.launch {
            try {
                val kursus = krs.getKursusById(idKursus)
                UpdateuiState = kursus.toUpdateUiState()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: HttpException) {
                e.printStackTrace()
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun updateKrs() {
        viewModelScope.launch {
            try {
                val kursus = UpdateuiState.updateUiEvent.toKrs()
                krs.updateKursus(kursus.idKursus, kursus)
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
    val idKursus: String = "",
    val namaKursus: String = "",
    val deskripsiK: String = "",
    val kategori: String = "",
    val harga: String = "",
    val idInstruktur: String = ""
)

fun UpdateUiEvent.toKrs(): Kursus = Kursus(
    idKursus = idKursus,
    namaKursus = namaKursus,
    deskripsiK = deskripsiK,
    kategori = kategori,
    harga = harga,
    idInstruktur = idInstruktur
)

fun Kursus.toUpdateUiState(): UpdateUiState = UpdateUiState(
    updateUiEvent = toUpdateUiEvent()
)

fun Kursus.toUpdateUiEvent(): UpdateUiEvent = UpdateUiEvent(
    idKursus = idKursus,
    namaKursus = namaKursus,
    deskripsiK = deskripsiK,
    kategori = kategori,
    harga = harga,
    idInstruktur = idInstruktur
)