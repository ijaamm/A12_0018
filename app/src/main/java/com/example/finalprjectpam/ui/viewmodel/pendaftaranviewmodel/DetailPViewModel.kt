package com.example.finalprjectpam.ui.viewmodel.pendaftaranviewmodel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalprjectpam.model.Pendaftaran
import com.example.finalprjectpam.repository.PendaftaranRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailUiState {
    data class Success(
        val pendaftaran: Pendaftaran
    ) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailPViewModel(private val pdft: PendaftaranRepository) : ViewModel() {
    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailUiState: StateFlow<DetailUiState> = _detailUiState

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getPendaftaranById(idPendaftaran: String) {
        viewModelScope.launch {
            _detailUiState.value = DetailUiState.Loading
            _detailUiState.value = try {
                val pendaftaran = pdft.getPendaftaranById(idPendaftaran)
                DetailUiState.Success(pendaftaran)
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun updatePdft(idPendaftaran: String, updatedPendaftaran: Pendaftaran) {
        viewModelScope.launch {
            try {
                pdft.updatePendaftaran(idPendaftaran, updatedPendaftaran)
                _detailUiState.value = DetailUiState.Success(updatedPendaftaran)
            } catch (e: IOException) {
                _detailUiState.value = DetailUiState.Error
            } catch (e: HttpException) {
                _detailUiState.value = DetailUiState.Error
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun deletePdft(idPendaftaran: String) {
        viewModelScope.launch {
            try {
                pdft.deletePendaftaran(idPendaftaran)
                _detailUiState.value = DetailUiState.Loading // Optionally, you can set it to Loading or Success based on your logic
            } catch (e: IOException) {
                _detailUiState.value = DetailUiState.Error
            } catch (e: HttpException) {
                _detailUiState.value = DetailUiState.Error
            }
        }
    }
}