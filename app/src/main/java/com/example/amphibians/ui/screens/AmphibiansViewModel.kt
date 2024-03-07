package com.example.amphibians.ui.screens

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.amphibians.model.AmphibianInfo
import com.example.amphibians.network.AmphibianApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AmphibiansUiState {
    data class Success(val amphibians: List<AmphibianInfo>): AmphibiansUiState
    object Error: AmphibiansUiState
    object Loading: AmphibiansUiState
}

class AmphibiansViewModel: ViewModel() {
    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set


    init {
        getAmphibians()
    }

    fun getAmphibians() {
        viewModelScope.launch {
            amphibiansUiState = AmphibiansUiState.Loading

            amphibiansUiState = try {
                val amphibians = AmphibianApi.retrofitService.getAmphibians()
                AmphibiansUiState.Success(amphibians)
            } catch (e: IOException) {
                AmphibiansUiState.Error
            }
        }
    }
}
