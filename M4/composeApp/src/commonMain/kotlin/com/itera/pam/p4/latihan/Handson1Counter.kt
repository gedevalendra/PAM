package com.itera.pam.p4.latihan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Latihan 1: Counter App dengan State (slide P4 hal. 29)
// Tugas: Buat counter dengan increment, decrement, dan reset.
//
// Checklist:
// [ ] State dengan remember
// [ ] mutableStateOf(0)
// [ ] Text untuk display
// [ ] Button increment (+1)
// [ ] Button decrement (-1)
// [ ] Button reset (ke 0)
// [ ] Disable decrement jika 0

@Composable
fun Handson1Screen() {
    // TODO: Deklarasikan state untuk count
    // var count by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Latihan 1: Counter App dengan State")

        // TODO: Tampilkan nilai count, contoh: Text("Count: $count")

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { /* TODO: decrement, jangan sampai < 0 */ }) {
                Text("-")
            }
            Button(onClick = { /* TODO: increment */ }) {
                Text("+")
            }
            Button(onClick = { /* TODO: reset ke 0 */ }) {
                Text("Reset")
            }
        }
    }
}
