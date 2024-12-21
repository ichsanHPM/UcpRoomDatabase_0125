package com.example.ucp2_125.ui.navigation

interface AlamatNavigasi {

    val route: String

    object DestinasiHome: AlamatNavigasi{
        override val route = "home"
    }

    object DestinasiDetail: AlamatNavigasi{
        override val route = "detail"
        const val Kode = "kode"
        val routeWitArg = "$route/{$Kode}"
    }

    object DestinasiUpdate: AlamatNavigasi{
        override val route = "update"
        const val Kode = "kode"
        val routeWitArg = "$route/{$Kode}"

    }
}