package com.example.finalprjectpam.ui.viewmodel.siswaviewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalprjectpam.model.Siswa
import com.example.finalprjectpam.repository.SiswaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeUiState{
    data class Success(
        val siswa: List<Siswa>
    ) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomeSViewModel(
    private val ssw: SiswaRepository
) : ViewModel() {
    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homeUiState: StateFlow<HomeUiState> = _homeUiState
    var sswUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getSiswa()
    }

    fun getSiswa() {
        viewModelScope.launch {
            sswUiState = HomeUiState.Loading
            sswUiState = try {
                HomeUiState.Success(ssw.getSiswa().data)
            } catch (e: IOException) {
                // Log error untuk IOException
                Log.e("GetSswError", "IOException occurred: ${e.message}", e)
                HomeUiState.Error
            } catch (e: HttpException) {
                // Log error untuk HttpException
                Log.e("GetSswError", "HttpException occurred: ${e.message}", e)
                HomeUiState.Error
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun updateSsw(idSiswa: String, updateSiswa : Siswa) {
        viewModelScope.launch {
            try {
                ssw.updateSiswa(idSiswa, updateSiswa)
                _homeUiState.value = HomeUiState.Success(listOf(updateSiswa))
            } catch (e: IOException) {
                _homeUiState.value = HomeUiState.Error
            } catch (e: android.net.http.HttpException) {
                _homeUiState.value = HomeUiState.Error
            }
        }
    }
}
