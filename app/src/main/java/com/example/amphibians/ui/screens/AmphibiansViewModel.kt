package com.example.amphibians.ui.screens

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.amphibians.network.AmphibianApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AmphibiansUiState {
    data class Success(val info: String): AmphibiansUiState
    object Error: AmphibiansUiState
    object Loading: AmphibiansUiState
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class AmphibiansViewModel: ViewModel() {
    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set


    init {
        getAmphibians()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getAmphibians() {
        viewModelScope.launch {
            amphibiansUiState = AmphibiansUiState.Loading

            amphibiansUiState = try {
                val listResult = AmphibianApi.retrofitService.getAmphibians()
                AmphibiansUiState.Success("Success: ${listResult.size} Amphibians info retrieved")
            } catch (e: IOException) {
                AmphibiansUiState.Error
            } catch (e: HttpException) {
                AmphibiansUiState.Error
            }
        }
    }
}
