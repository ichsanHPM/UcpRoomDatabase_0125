package com.example.ucp2_125.data.entity

import androidx.room.PrimaryKey

data class dosen(
    @PrimaryKey
    val Nidn: String,
    val Nama: String,
    val Jenis_Kelamin: String
)
