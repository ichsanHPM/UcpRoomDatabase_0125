package com.example.ucp2_125.data.entity

import androidx.room.PrimaryKey

data class matakuliah(
    @PrimaryKey
    val Kode : String,
    val Nama : String,
    val SKS : String,
    val Semester : String,
    val Jenis : String,
    val DosenPengampu : String

)
