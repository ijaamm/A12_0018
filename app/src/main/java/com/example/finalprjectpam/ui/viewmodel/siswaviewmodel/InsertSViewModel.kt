package com.example.finalprjectpam.ui.viewmodel.siswaviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprjectpam.model.Siswa
import com.example.finalprjectpam.repository.SiswaRepository
import kotlinx.coroutines.launch

class InsertSViewModel(
    private val ssw: SiswaRepository
) : ViewModel() {
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun updateInsertSswState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertSsw(){
        viewModelScope.launch {
            try {
                ssw.insertSiswa(uiState.insertUiEvent.toSsw())
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
    val idSiswa: String = "",
    val namaSiswa: String = "",
    val emailS: String = "",
    val noTeleponS: String = ""
)

fun InsertUiEvent.toSsw(): Siswa = Siswa(
    idSiswa = idSiswa,
    namaSiswa = namaSiswa,
    emailS = emailS,
    noTeleponS = noTeleponS
)

fun Siswa.toUiStateSsw(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Siswa.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    idSiswa = idSiswa,
    namaSiswa = namaSiswa,
    emailS = emailS,
    noTeleponS = noTeleponS
)