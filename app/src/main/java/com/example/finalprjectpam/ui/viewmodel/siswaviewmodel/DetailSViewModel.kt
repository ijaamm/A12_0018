package com.example.finalprjectpam.ui.viewmodel.siswaviewmodel

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprjectpam.model.Siswa
import com.example.finalprjectpam.repository.SiswaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okio.IOException

sealed class DetailUiState {
    data class Success(val siswa: Siswa) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailSViewModel(
    private val ssw: SiswaRepository
) : ViewModel() {
    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailUiState: StateFlow<DetailUiState> = _detailUiState

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getSiswaById(idSiswa: String) {
        viewModelScope.launch {
            _detailUiState.value = DetailUiState.Loading
            _detailUiState.value = try {
                val siswa = ssw.getSiswaById(idSiswa)
                DetailUiState.Success(siswa)
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun updateSsw(idSiswa: String, updateSiswa: Siswa) {
        viewModelScope.launch {
            try {
                ssw.updateSiswa(idSiswa, updateSiswa)
                _detailUiState.value = DetailUiState.Success(updateSiswa)
            } catch (e: IOException) {
                _detailUiState.value = DetailUiState.Error
            } catch (e: HttpException) {
                _detailUiState.value = DetailUiState.Error
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun deleteSsw(idSiswa: String) {
        viewModelScope.launch {
            try {
                ssw.deleteSiswa(idSiswa)
                _detailUiState.value = DetailUiState.Loading
            } catch (e: IOException) {
                _detailUiState.value = DetailUiState.Error
            } catch (e: HttpException) {
                _detailUiState.value = DetailUiState.Error
            }
        }
    }
}