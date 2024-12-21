package com.example.ucp2_125.repository

import com.example.ucp2_125.data.dao.dosendao
import com.example.ucp2_125.data.entity.dosen
import kotlinx.coroutines.flow.Flow

class localrepositorydosen (
    private val  dosendao: dosendao) : repositorydosen
{
    override fun getAlldosen(): Flow<List<dosen>>{
        return dosendao.getAlldosen()
    }

    override fun getdosen(Nidn: String): Flow<dosen>{
        return dosendao.getdosen(Nidn)
    }

    override suspend fun insertdosen(dosen: dosen){
        dosendao.insertdosen(dosen)
    }

}