package com.example.ucp2_125.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_125.data.entity.dosen
import com.example.ucp2_125.repository.repositorydosen
import kotlinx.coroutines.launch

class dosenViewModel(private val repositorydosen: repositorydosen): ViewModel(){
    var uiState by mutableStateOf(dosenUIState())

    fun updateState(dosenEvent: dosenEvent){
        uiState = uiState.copy(
            dosenEvent = dosenEvent
        )
    }

    private fun validateFields(): Boolean{
        val event = uiState.dosenEvent
        val errorState = FormErrorState(
            Nidn = if (event.Nidn.isNotEmpty()) null else "Nidn tidak boleh kosong",
            Nama = if (event.Nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            Jenis_Kelamin = if (event.Jenis_Kelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong"
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }
    fun saveData(){
        val currentEvent = uiState.dosenEvent
        if (validateFields()){
            viewModelScope.launch{
                try{
                    repositorydosen.insertdosen(currentEvent.todosenEntity())
                    uiState = uiState.copy(
                        snackbarMessage = "Data berhasil disimpan",
                        dosenEvent = dosenEvent(),
                        isEntryValid = FormErrorState()
                    )
                }catch (e: Exception){
                    uiState = uiState.copy(snackbarMessage = "Data gagal disimpan")
                }
            }
        }else{
            uiState = uiState.copy(snackbarMessage = "Input tidak valid periksa kembali data")
        }
    }
    fun resetSnackBarMessage(){
        uiState = uiState.copy(snackbarMessage = null)
    }

}

data class dosenUIState(
    val dosenEvent: dosenEvent = dosenEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackbarMessage: String? = null
)

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