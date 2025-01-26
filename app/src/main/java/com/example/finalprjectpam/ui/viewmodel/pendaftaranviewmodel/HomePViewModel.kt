package com.example.finalprjectpam.ui.viewmodel.pendaftaranviewmodel

import android.util.Log
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

sealed class HomeUiState{
    data class Success(
        val pendaftaran: List<Pendaftaran>
    ) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomePViewModel(private val pdft: PendaftaranRepository) : ViewModel() {
    var pdftUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getPdft()
    }

    fun getPdft() {
        viewModelScope.launch {
            pdftUiState = HomeUiState.Loading
            pdftUiState = try {
                HomeUiState.Success(pdft.getPendaftaran().data)
            } catch (e: IOException) {
                // Log error untuk IOException
                Log.e("GetMhsError", "IOException occurred: ${e.message}", e)
                HomeUiState.Error
            } catch (e: HttpException) {
                // Log error untuk HttpException
                Log.e("GetMhsError", "HttpException occurred: ${e.message}", e)
                HomeUiState.Error
            }
        }
    }


    fun deletePdft(idPendaftaran: String){
        viewModelScope.launch {
            try {
                pdft.deletePendaftaran(idPendaftaran)
            } catch (e: IOException){
                HomeUiState.Error
            } catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }
}