package com.example.finalprjectpam.ui.viewmodel.kursusviewmodel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalprjectpam.model.Kursus
import com.example.finalprjectpam.repository.KursusRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailUiState {
    data class Success(
        val kursus: Kursus
    ) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailKViewModel(private val krs: KursusRepository) : ViewModel() {
    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailUiState: StateFlow<DetailUiState> = _detailUiState

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getKursusById(idKursus: String) {
        viewModelScope.launch {
            _detailUiState.value = DetailUiState.Loading
            _detailUiState.value = try {
                val kursus = krs.getKursusById(idKursus)
                DetailUiState.Success(kursus)
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun updateKrs(idKursus: String, updatedKursus: Kursus) {
        viewModelScope.launch {
            try {
                krs.updateKursus(idKursus, updatedKursus)
                _detailUiState.value = DetailUiState.Success(updatedKursus)
            } catch (e: IOException) {
                _detailUiState.value = DetailUiState.Error
            } catch (e: HttpException) {
                _detailUiState.value = DetailUiState.Error
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun deleteKrs(idKursus: String) {
        viewModelScope.launch {
            try {
                krs.deleteKursus(idKursus)
                _detailUiState.value = DetailUiState.Loading
            } catch (e: IOException) {
                _detailUiState.value = DetailUiState.Error
            } catch (e: HttpException) {
                _detailUiState.value = DetailUiState.Error
            }
        }
    }
}