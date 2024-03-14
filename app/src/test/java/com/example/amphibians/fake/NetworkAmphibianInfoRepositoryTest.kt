package com.example.amphibians.fake

import com.example.amphibians.data.NetworkAmphibianInfoRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test


class NetworkAmphibianInfoRepositoryTest {
    @Test
    fun networkAmphibianInfoRepository_getAmphibianInfo_verifyAmphibianInfoList() = runTest {
        val repository = NetworkAmphibianInfoRepository(
            amphibiansInfoApiService =  FakeAmphibiansApiService()
        )
        assertEquals(FakeDataSource.amphibianInfoList, repository.getAmphibianInfo())
    }
}