package com.example.ucp2_125.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2_125.data.entity.dosen
import kotlinx.coroutines.flow.Flow

@Dao
interface dosendao {
    //mengambil semua data dosen  dari tabel dosen  diurutkan secara ASC
    @Query("SELECT * FROM dosen ORDER BY nama ASC")
    fun getAlldosen() : Flow<List<dosen>>

    @Query("SELECT * FROM dosen WHERE nidn = :nidn")
    fun getdosen(nidn: String) : Flow<dosen>

    @Insert
    suspend fun insertdosen(
        dosen: dosen
    )

}