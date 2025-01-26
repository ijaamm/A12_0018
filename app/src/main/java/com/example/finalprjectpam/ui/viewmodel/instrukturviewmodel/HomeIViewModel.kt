package com.example.finalprjectpam.ui.viewmodel.instrukturviewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalprjectpam.model.Instruktur
import com.example.finalprjectpam.model.Siswa
import com.example.finalprjectpam.repository.InstrukturRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeUiState{
    data class Success(
        val instruktur: List<Instruktur>
    ) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomeIViewModel(
    private val ins: InstrukturRepository
) : ViewModel() {
    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homeUiState: StateFlow<HomeUiState> = _homeUiState
    var insUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getIns()
    }

    fun getIns() {
        viewModelScope.launch {
            insUiState = HomeUiState.Loading
            insUiState =
                try {
                HomeUiState.Success(ins.getInstruktur().data)
            } catch (e: IOException) {
                // Log error untuk IOException
                Log.e("GetInsError", "IOException occurred: ${e.message}", e)
                HomeUiState.Error
            } catch (e: HttpException) {
                // Log error untuk HttpException
                Log.e("GetInsError", "HttpException occurred: ${e.message}", e)
                HomeUiState.Error
            }
        }
    }


    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun updateIns(idInstruktur: String, updateInstruktur : Instruktur) {
        viewModelScope.launch {
            try {
                ins.updateInstruktur(idInstruktur, updateInstruktur)
                _homeUiState.value =HomeUiState.Success(listOf(updateInstruktur))
            } catch (e: IOException) {
                _homeUiState.value = HomeUiState.Error
            } catch (e: android.net.http.HttpException) {
                _homeUiState.value = HomeUiState.Error
            }
        }
    }
}