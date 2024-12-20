package com.example.ucp2_125.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ucp2_125.data.entity.dosen
import com.example.ucp2_125.data.entity.matakuliah

//mendefinisikan database dengan tabel matakuliah dan dosen
@Database(entities = [matakuliah::class, dosen::class], version = 1, exportSchema = false)
abstract class KrsDatabase: RoomDatabase() {


}