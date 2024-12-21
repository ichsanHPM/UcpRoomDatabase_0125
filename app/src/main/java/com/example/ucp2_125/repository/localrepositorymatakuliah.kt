package com.example.ucp2_125.repository

import com.example.ucp2_125.data.dao.matakuliahdao
import com.example.ucp2_125.data.entity.matakuliah
import kotlinx.coroutines.flow.Flow

class localrepositorymatakuliah (
    private val matakuliahdao: matakuliahdao) :
    repositorymatakuliah
{
    override fun getAllmatakuliah(): Flow<List<matakuliah>>{
        return matakuliahdao.getAllmatakuliah()
    }

    override fun getmatakuliah(Kode: String): Flow<matakuliah>{
        return matakuliahdao.getmatakuliah(Kode)
    }

    override suspend fun insertmatakuliah(matakuliah: matakuliah){
        matakuliahdao.insertmatakuliah(matakuliah)
    }

    override suspend fun deletematakuliah(matakuliah: matakuliah){
        matakuliahdao.deletematakuliah(matakuliah)
    }

    override suspend fun updatematakuliah(matakuliah: matakuliah){
        matakuliahdao.updatematakuliah(matakuliah)
    }
}