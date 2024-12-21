package com.example.ucp2_125.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2_125.data.dao.dosendao
import com.example.ucp2_125.data.dao.matakuliahdao
import com.example.ucp2_125.data.entity.dosen
import com.example.ucp2_125.data.entity.matakuliah

//mendefinisikan database dengan tabel matakuliah dan dosen
@Database(entities = [matakuliah::class, dosen::class], version = 1, exportSchema = false)
abstract class KrsDatabase: RoomDatabase() {
    abstract fun dosendao(): dosendao
    abstract fun matakuliahdao(): matakuliahdao

    companion object{
        @Volatile
        private var Instance: KrsDatabase? = null

        fun getDatabase(context: Context): KrsDatabase{
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    KrsDatabase::class.java,
                    "KrsDatabase"
                ).build().also {Instance = it}
            })
        }
    }
}