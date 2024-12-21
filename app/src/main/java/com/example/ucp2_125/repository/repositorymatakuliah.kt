package com.example.ucp2_125.repository

import com.example.ucp2_125.data.entity.matakuliah
import kotlinx.coroutines.flow.Flow

interface repositorymatakuliah {

    fun getAllmatakuliah() : Flow<List<matakuliah>>

    fun getmatakuliah(Kode: String): Flow<matakuliah>

    suspend fun insertmatakuliah(matakuliah: matakuliah)

    suspend fun deletematakuliah(matakuliah: matakuliah)

    suspend fun updatematakuliah(matakuliah: matakuliah)
}