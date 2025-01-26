package com.example.finalprjectpam.ui.viewmodel.siswaviewmodel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalprjectpam.model.Siswa
import com.example.finalprjectpam.repository.SiswaRepository
import kotlinx.coroutines.launch
import okio.IOException

class UpdateSViewModel(
    private val ssw: SiswaRepository
) : ViewModel() {
    var UpdateuiState by mutableStateOf(UpdateUiState())
        private set

    fun updateState(updateUiEvent: UpdateUiEvent) {
        UpdateuiState = UpdateUiState(updateUiEvent = updateUiEvent)
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun loadSiswa(idSiswa: String) {
        viewModelScope.launch {
            try {
                val siswa = ssw.getSiswaById(idSiswa)
                UpdateuiState = siswa.toUpdateUiState()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: HttpException) {
                e.printStackTrace()
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun updateSsw() {
        viewModelScope.launch {
            try {
                val siswa = UpdateuiState.updateUiEvent.toSsw()
                ssw.updateSiswa(siswa.idSiswa, siswa)
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
    val idSiswa: String = "",
    val namaSiswa: String = "",
    val emailS: String = "",
    val noTeleponS: String = ""
)

fun UpdateUiEvent.toSsw(): Siswa = Siswa(
    idSiswa = idSiswa,
    namaSiswa = namaSiswa,
    emailS = emailS,
    noTeleponS = noTeleponS
)

fun Siswa.toUpdateUiState(): UpdateUiState = UpdateUiState(
    updateUiEvent = toUpdateUiEvent()
)

fun Siswa.toUpdateUiEvent(): UpdateUiEvent = UpdateUiEvent(
    idSiswa = idSiswa,
    namaSiswa = namaSiswa,
    emailS = emailS,
    noTeleponS = noTeleponS
)