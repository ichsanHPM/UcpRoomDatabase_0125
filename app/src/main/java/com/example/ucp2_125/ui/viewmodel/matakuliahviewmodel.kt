package com.example.ucp2_125.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_125.data.entity.dosen
import com.example.ucp2_125.data.entity.matakuliah
import com.example.ucp2_125.repository.repositorydosen
import com.example.ucp2_125.repository.repositorymatakuliah
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class matakuliahviewmodel(private val repositorymatakuliah: repositorymatakuliah,
    private val repositorydosen: repositorydosen) : ViewModel(){

        var uiState by mutableStateOf(matakuliahUiState())

        val dosenList: StateFlow<List<dosen>> = repositorydosen.getAlldosen()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

        var dosenInsert by mutableStateOf<List<String>>(emptyList())
            private set

        fun fetchnamadosen(){
            viewModelScope.launch{
                try{
                    repositorydosen.getAlldosen()
                        .collect{dosenlist->
                            dosenInsert = dosenlist.map {it.Nama}
                        }
                }catch (e: Exception){
                    dosenInsert = emptyList()
                }
            }
        }

        fun updateState(matakuliahEvent: matakuliahEvent){
            uiState = uiState.copy(
                matakuliahEvent = matakuliahEvent
            )
        }

        private fun validateFields(): Boolean{
            val event = uiState.matakuliahEvent
            val errorState = matakuliahErrorState(
                Kode = if (event.Kode.isNotEmpty()) null else "Kode tidak boleh kosong",
                Nama = if (event.Nama.isNotEmpty()) null else "Nama matakuliah tidak boleh kosong",
                SKS = if (event.SKS.isNotEmpty()) null else "SKS tidak boleh kosong",
                Semester = if (event.Semester.isNotEmpty()) null else "Semester tidak boleh kosong",
                Jenis = if (event.Jenis.isNotEmpty()) null else "Jenis matakuliah tidak boleh kosong",
                DosenPengampu = if (event.DosenPengampu.isNotEmpty()) null else "Dosen Pengampu tidak boleh kosong"
            )
            uiState = uiState.copy(isEntryValid = errorState)
            return errorState.isValid()
        }
        fun saveData(){
            val currentEvent = uiState.matakuliahEvent
            if (validateFields()){
                viewModelScope.launch{
                    try{
                        repositorymatakuliah.insertmatakuliah(currentEvent.tomatakuliahEntity())
                        uiState = uiState.copy(
                            snackbarMessage = "Data berhasil disimpan",
                            matakuliahEvent = matakuliahEvent(),
                            isEntryValid = matakuliahErrorState()
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

data class matakuliahUiState(
    val matakuliahEvent: matakuliahEvent = matakuliahEvent(),
    val isEntryValid: matakuliahErrorState = matakuliahErrorState(),
    val snackbarMessage: String? = null
)

data class matakuliahErrorState(
    val Kode: String? = null,
    val Nama: String? = null,
    val SKS: String? = null,
    val Semester: String? = null,
    val Jenis: String? = null,
    val DosenPengampu: String? = null
){
    fun isValid(): Boolean{
        return Kode == null && Nama == null && SKS == null && Semester == null && Jenis == null && DosenPengampu == null
    }
}

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