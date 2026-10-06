# Hands-on Pertemuan 4 — State Management (MVVM)

Proyek Kotlin Multiplatform (KMP) + Compose Multiplatform untuk 3 latihan praktikum Pertemuan 4 (IF25-22017 — Pengembangan Aplikasi Mobile, ITERA). Struktur proyek ini sama seperti `P1 - Pengenalan MK dan Setup Environment - Hands-on/`: satu modul `composeApp` dengan source set `commonMain`/`androidMain`/`iosMain`/`desktopMain`.

Ketiga latihan di sini diambil langsung dari slide `P4 - State Management MVVM.pdf` (halaman 29-31, bagian "Hands-on Practice"), bukan latihan buatan baru — supaya konsisten dengan yang diajarkan di kelas.

## Capaian Pembelajaran

Sesuai CPMK0501 (indikator: *mampu mengelola state dan menerapkan MVVM pattern*):

- Memahami konsep `State` dalam Compose dan cara mengelolanya
- Menggunakan `remember` dan `mutableStateOf` dengan benar
- Menerapkan **state hoisting** untuk membuat komponen yang reusable
- Memahami kapan dan mengapa **recomposition** terjadi
- Mengimplementasikan **MVVM pattern** dengan `ViewModel` + `StateFlow`

## Cara Menjalankan

1. Buka folder **"P4 - State Management MVVM - Hands-on"** ini di Android Studio (`File > Open`) sebagai proyek terpisah, tunggu Gradle sync selesai.
2. Pilih salah satu run configuration:
   - **Android** — pilih configuration `composeApp`, pilih emulator/device, klik ▶️ (atau `./gradlew :composeApp:installDebug`).
   - **Desktop** — jalankan `./gradlew :composeApp:run` dari terminal.
   - **iOS** *(khusus Mac + Xcode)* — proyek ini **tidak menyertakan** folder `iosApp/`. Buat proyek baru lewat [kmp.jetbrains.com](https://kmp.jetbrains.com), lalu salin isi `composeApp/src/` dari folder ini.
3. Di `App.kt` ada menu untuk berpindah antara Latihan 1-3 dan Solusi 1-3 (state sederhana, bukan Navigation Component — itu materi Pertemuan 5).
4. Buka file di `composeApp/src/commonMain/kotlin/.../latihan/`, lengkapi bagian `TODO`, jalankan ulang untuk melihat hasilnya. Bandingkan dengan `solusi/` yang berpasangan jika stuck.

## Daftar Latihan

### 1. `Handson1Counter.kt` — Counter App dengan State
Buat counter dengan tombol increment (+1), decrement (-1, disable jika sudah 0), dan reset (ke 0).

- **Konsep:** `remember`, `mutableStateOf`, recomposition, `Button(enabled = ...)`.

### 2. `Handson2RegistrationForm.kt` — Form dengan State Hoisting
Buat `LabeledTextField` yang **stateless** (state-nya di-hoist ke parent `RegistrationForm`/`Handson2Screen`), lalu pakai komponen yang sama untuk field Name dan Email, dan tampilkan preview "Hello, [name]! Email: [email]".

- **Konsep:** state hoisting (state turun ↓ lewat parameter, event naik ↑ lewat callback), komponen reusable & testable.

### 3. `Handson3TodoApp.kt` — Todo App dengan ViewModel (MVVM)
Implementasikan `TodoViewModel` (Model: `Todo`/`TodoUiState`, ViewModel: `MutableStateFlow` private + `StateFlow` public, View: Compose UI dengan `collectAsState()`), lengkap dengan fungsi tambah todo dan toggle status selesai.

- **Catatan:** UI-nya sengaja belum menampilkan form/list apa pun sebelum semua TODO di `TodoViewModel` dan Composable-nya dilengkapi.
- **Konsep:** MVVM (Model-View-ViewModel), `ViewModel`, `MutableStateFlow`/`StateFlow`, `viewModel { }` composable factory, `LazyColumn`, `Checkbox`.

## Troubleshooting

Diambil langsung dari slide P4 hal. 32:

| Error | Solusi |
|---|---|
| UI tidak update saat state berubah | Pastikan menggunakan `remember { mutableStateOf() }`, bukan variable biasa |
| State reset setiap recomposition | Wrap nilai dengan `remember { }` |
| `Unresolved reference: by` | Import `androidx.compose.runtime.getValue` dan `androidx.compose.runtime.setValue` |
| `collectAsState()` not found | Import `androidx.compose.runtime.collectAsState` |
| `ViewModel` not found | Tambahkan dependency `org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose` (sudah ada di `composeApp/build.gradle.kts` proyek ini) |

## Catatan: Tugas Praktikum Minggu 4 (terpisah dari hands-on ini)

Ada **Tugas Praktikum Minggu 4** (bobot 4%, deadline sebelum Pertemuan 5): kembangkan "Profile App" dari tugas minggu lalu dengan (1) `ProfileViewModel` + `ProfileUiState` (MVVM), (2) fitur edit profile (form dengan state hoisting, save button), (3) dark mode toggle (state disimpan di ViewModel). Bonus +10% untuk dark mode theme yang smooth. Lihat slide `P4 - State Management MVVM.pdf` halaman 35-36 untuk rubrik penilaian lengkap.

## Sumber Pustaka

- [State and Jetpack Compose](https://developer.android.com/jetpack/compose/state)
- [ViewModel Overview](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [Guide to App Architecture](https://developer.android.com/topic/architecture)
- [State in Jetpack Compose (Codelab)](https://developer.android.com/codelabs/jetpack-compose-state)
