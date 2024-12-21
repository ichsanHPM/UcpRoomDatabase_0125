package com.example.ucp2_125.repository

import com.example.ucp2_125.data.entity.dosen
import kotlinx.coroutines.flow.Flow

interface repositorydosen {

    fun getAlldosen() : Flow<List<dosen>>

    fun getdosen(Nidn: String): Flow<dosen>

    suspend fun insertdosen(dosen: dosen)
}