package com.example.modaldrawer_compose

sealed class Routes(val route: String) {
    object PantallaHome: Routes("inicio")
    object PantallaUSer: Routes("usuario")
    object PantallaFavorite: Routes("favorito")
}
