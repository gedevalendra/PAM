# Pengembangan Aplikasi Mobile - News Feed Simulator

-  **Nama  :** Gede Valendra
-  **NIM     :** ```124140142```
-  **Kelas    :** Pengembangan APlikasi Mobile RB

## Fitur yang Diimplementasikan

1. **Flow Simulasi Berita :** Menggunakan `flow {}` dan `delay(2000)` untuk menghasilkan data berita baru secara *asynchronous* setiap 2 detik.
2. **Filter Kategori:** Menggunakan operator `.filter { .. }` untuk menyaring berita berdasarkan kategori yang dipilih pengguna (Semua, Politik, Kesehatan, Pendidikan, Ekonomi).
3. **Transformasi Data:** Menggunakan `.map { .. }` (atau diintegrasikan dengan logika UI) untuk mengubah format mentah menjadi objek UI yang rapi.
4. **StateFlow Counter:** Menggunakan `MutableStateFlow` untuk melacak, menyimpan, dan memperbarui jumlah berita yang sudah dibaca secara *real-time*.
5. **Coroutines Async Detail:** Menggunakan `coroutineScope.launch` dan fungsi `suspend` dengan `delay(1200)` untuk mengambil detail isi teks berita..

## Prasyarat (Prerequisites)

Sebelum menjalankan proyek ini, pastikan sistem Anda telah memiliki:
* **IDE :** Android Studio.
* **Java Development Kit :** Minimal menggunakan versi 21.
* **Plugin :** Kotlin Multiplatform.

## Cara Menjalankan Proyek (Android Studio)

1. **Clone Repositori :** Buka terminal dan lakukan proses *clone* repositori ini ke komputer lokal Anda:
```bash
git clone https://github.com/gedevalendra/PAW.git
```
2. **Akses :** Jika muncul *pop-up* dialog keamanan yang bertanya "*Trust and Open Project?*", klik tombol **Trust Project**.
3. **Sinkronisasi Gradle :** Perhatikan baris status di pojok kanan bawah layar. Android Studio akan otomatis mengunduh seluruh file Gradle dan dependensi Compose yang dibutuhkan. Jangan melakukan perubahan kode apa pun sampai *loading bar* selesai dan muncul tulisan *Gradle Sync Finished* atau *BUILD SUCCESSFUL*.
4. **Periksa Versi JDK :** Untuk menghindari *error* kompatibilitas, pastikan pengaturan Java sudah tepat. Tekan **(Ctrl + Alt + S)**, masuk ke **Build, Execution, Deployment > Build Tools > Gradle**, dan pastikan **Gradle JDK** menggunakan versi **21**.
5. **Siapkan Perangkat :** Pada kotak *dropdown* di sebelah target konfigurasi, pilih emulator (misalnya *Pixel 7 API 34*) atau *smartphone* fisik yang sudah diaktifkan mode *USB Debugging*-nya. Jika kotaknya kosong, buka panel **Device Manager** di sebelah kanan layar dan klik ikon **+** untuk membuat *Virtual Device* baru.
6. **Jalankan Aplikasi :** Klik tombol **Play (Run)** berwarna hijau atau tekan **Shift + F10**. Tunggu proses kompilasi berjalan, dan antarmuka *News Feed Simulator* akan otomatis terbuka di layar emulator atau perangkat Anda.