package com.example.finalprjectpam.ui.viewmodel.instrukturviewmodel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalprjectpam.model.Instruktur
import com.example.finalprjectpam.repository.InstrukturRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okio.IOException

sealed class DetailIUiState {
    data class Success(val instruktur: Instruktur) : DetailIUiState()
    object Error : DetailIUiState()
    object Loading : DetailIUiState()
}

class DetailIViewModel(
    private val ins: InstrukturRepository
) : ViewModel() {
    private val _detailIUiState = MutableStateFlow<DetailIUiState>(DetailIUiState.Loading)
    val detailIUiState: StateFlow<DetailIUiState> = _detailIUiState

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getInstrukturById(idInstruktur: String) {
        viewModelScope.launch {
            _detailIUiState.value = DetailIUiState.Loading
            _detailIUiState.value = try {
                val instruktur = ins.getInstrukturById(idInstruktur)
                DetailIUiState.Success(instruktur)
            } catch (e: IOException) {
                DetailIUiState.Error
            } catch (e: HttpException) {
                DetailIUiState.Error
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun updateInstruktur(idInstruktur: String, updateInstruktur: Instruktur) {
        viewModelScope.launch {
            try {
                ins.updateInstruktur(idInstruktur, updateInstruktur)
                _detailIUiState.value = DetailIUiState.Success(updateInstruktur)
            } catch (e: IOException) {
                _detailIUiState.value = DetailIUiState.Error
            } catch (e: HttpException) {
                _detailIUiState.value = DetailIUiState.Error
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun deleteInstruktur(idInstruktur: String) {
        viewModelScope.launch {
            try {
                ins.deleteInstruktur(idInstruktur)
                _detailIUiState.value = DetailIUiState.Loading // Optionally, you can set it to Loading or Success based on your logic
            } catch (e: IOException) {
                _detailIUiState.value = DetailIUiState.Error
            } catch (e: HttpException) {
                _detailIUiState.value = DetailIUiState.Error
            }
        }
    }
}