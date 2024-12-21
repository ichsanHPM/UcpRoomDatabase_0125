package com.example.ucp2_125.ui.viewmodel

import com.example.ucp2_125.data.entity.dosen

data class FormErrorState(
    val Nidn: String? = null,
    val Nama: String? = null,
    val Jenis_Kelamin: String? = null,
){
    fun isValid(): Boolean{
        return Nidn == null && Nama == null && Jenis_Kelamin == null
    }
}

fun dosenEvent.todosenEntity():dosen = dosen(
    Nidn = Nidn,
    Nama = Nama,
    Jenis_Kelamin = Jenis_Kelamin
)

data class dosenEvent(
    val Nidn: String = "",
    val Nama: String = "",
    val Jenis_Kelamin: String = ""
)