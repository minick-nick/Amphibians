package com.example.amphibians.data

import com.example.amphibians.model.AmphibianInfo
import com.example.amphibians.network.AmphibiansApiService

interface AmphibianInfoRepository {
    suspend fun getAmphibianInfo(): List<AmphibianInfo>
}

class NetworkAmphibianInfoRepository(private val amphibiansInfoApiService: AmphibiansApiService):
    AmphibianInfoRepository {

    override suspend fun getAmphibianInfo(): List<AmphibianInfo> {
        return amphibiansInfoApiService.getAmphibians()
    }


}
