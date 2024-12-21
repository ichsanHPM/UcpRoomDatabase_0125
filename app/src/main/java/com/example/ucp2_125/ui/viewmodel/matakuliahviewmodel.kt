package com.example.ucp2_125.ui.viewmodel

import com.example.ucp2_125.data.entity.matakuliah

data class matakuliahErrorState(
    val Kode: String? = null,
    val Nama: String? = null,
    val SKS: String? = null,
    val Semester: String? = null,
    val Jenis: String? = null,
    val DosenPengampu: String? = null
)

fun matakuliahEvent.tomatakuliahEntity(): matakuliah = matakuliah(
    Kode = Kode,
    Nama = Nama,
    SKS = SKS,
    Semester = Semester,
    Jenis = Jenis,
    DosenPengampu = DosenPengampu
)

data class matakuliahEvent(
    val Kode: String = "",
    val Nama: String = "",
    val SKS: String = "",
    val Semester: String = "",
    val Jenis: String = "",
    val DosenPengampu: String = ""

)