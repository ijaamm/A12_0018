package com.example.finalprjectpam.ui.viewmodel.kursusviewmodel

import android.util.Log
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

sealed class HomeUiState{
    data class Success(
        val kursus: List<Kursus>
    ) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomeKViewModel(
    private val krs: KursusRepository
) : ViewModel() {
    var krsUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getKrs()
    }

    fun getKrs() {
        viewModelScope.launch {
            krsUiState = HomeUiState.Loading
            krsUiState =
                try {
                    HomeUiState.Success(krs.getKursus().data)
                } catch (e: IOException) {
                // Log error untuk IOException
                Log.e("GetKrsError", "IOException occurred: ${e.message}", e)
                HomeUiState.Error
            } catch (e: HttpException) {
                // Log error untuk HttpException
                Log.e("GetKrsError", "HttpException occurred: ${e.message}", e)
                HomeUiState.Error
            }
        }
    }


    fun deleteKrs(idKursus: String){
        viewModelScope.launch {
            try {
                krs.deleteKursus(idKursus)
            } catch (e: IOException){
                HomeUiState.Error
            } catch (e: HttpException){
                HomeUiState.Error
            }
        }
    }
}