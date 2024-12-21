package com.example.ucp2_125.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_125.data.entity.matakuliah
import com.example.ucp2_125.repository.repositorymatakuliah
import com.example.ucp2_125.ui.navigation.AlamatNavigasi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class detailmatakuliahviewmodel (
    savedStateHandle: SavedStateHandle,
    private val repositorymatakuliah: repositorymatakuliah
) : ViewModel(){
    private val _Kode: String = checkNotNull(savedStateHandle[AlamatNavigasi.DestinasiDetailmatakuliah.Kode])
    val detailUiState: StateFlow<DetailUiState> = repositorymatakuliah.getmatakuliah(_Kode)
        .filterNotNull()
        .map{
            DetailUiState(
                detailUiEvent = it.toDetailUiEvent(),
                isLoading = false
            )
        }
        .onStart{
            emit(
                DetailUiState(isLoading = true)
            )
            delay(600)
        }
        .catch {
            emit(
                DetailUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUiState(
                isLoading = true
            )
        )
    fun deletematakuliah(){
        detailUiState.value.detailUiEvent.tomatakuliahEntity().let {
            viewModelScope.launch{
                repositorymatakuliah.deletematakuliah(it)
            }
        }
    }
}

data class DetailUiState(
    val detailUiEvent: matakuliahEvent = matakuliahEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == matakuliahEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != matakuliahEvent()
}

fun matakuliah.toDetailUiEvent():matakuliahEvent{
    return matakuliahEvent(
        Kode = Kode,
        Nama = Nama,
        SKS = SKS,
        Semester = Semester,
        Jenis = Jenis,
        DosenPengampu = DosenPengampu
    )
}