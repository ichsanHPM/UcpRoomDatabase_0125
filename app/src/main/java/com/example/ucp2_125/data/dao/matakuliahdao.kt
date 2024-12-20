package com.example.ucp2_125.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2_125.data.entity.matakuliah
import kotlinx.coroutines.flow.Flow

@Dao
interface matakuliahdao {

    @Query("SELECT * FROM matakuliah ORDER BY kode ASC")
    fun getAllmatakuliah() : Flow<List<matakuliah>>

    @Query("SELECT * FROM matakuliah WHERE kode = :kode")
    fun getmatakuliah(kode: String) : Flow<matakuliah>

    @Insert
    suspend fun insertmatakuliah(matakuliah: matakuliah)

    @Update
    suspend fun updatematakuliah(matakuliah: matakuliah)

    @Delete
    suspend fun deletematakuliah(matakuliah: matakuliah)

}