package com.example.ucp2_125.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matakuliah")
data class matakuliah(
    @PrimaryKey
    val Kode : String,
    val Nama : String,
    val SKS : String,
    val Semester : String,
    val Jenis : String,
    val DosenPengampu : String

)
