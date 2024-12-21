package com.example.ucp2_125.ui.navigation

interface AlamatNavigasi {

    val route: String

    object DestinasiHomedosen: AlamatNavigasi{
        override val route = "home"
    }

    object DestinasiDetaildosen: AlamatNavigasi{
        override val route = "detail"
        const val Nidn = "Nidn"
        val routeWithArg = "$route/{$Nidn}"
    }

    object DestinationDetailmatakuliah: AlamatNavigasi{
        override val route = "detail"
        const val Kode = "Kode"
        val routeWithArg = "$route/{$Kode}"
    }

    object DestinasiUpdatematakuliah: AlamatNavigasi{
        override val route = "update"
        const val Kode = "kode"
        val routeWitArg = "$route/{$Kode}"
    }

    object DestinasiMailScreen: AlamatNavigasi{
        override val route = "utama"
    }

    object DestinasiHomematakuliah: AlamatNavigasi{
        override val route = "homematakuliah"
    }
}