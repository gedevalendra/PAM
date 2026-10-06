package com.itera.pam.p4

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Hands-on Pertemuan 4 — State Management (MVVM)") {
        App()
    }
}
