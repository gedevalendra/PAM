package com.example.m2

import kotlinx.coroutines.ExperimentalCoroutinesApi
import androidx.compose.ui.graphics.Color
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class News(val id: Int, val title: String, val category: String, val snippet: String)

class NewsViewModel {
    private val _readCount = MutableStateFlow(0)
    val readCount: StateFlow<Int> = _readCount.asStateFlow()

    private val categories = listOf("Politik", "Kesehatan", "Pendidikan", "Ekonomi")

    private val judulBerita = listOf(
        "Kemenkeu Rilis Aturan Baru Pajak UMKM 2026",
        "Kasus Varian Flu Baru Ditemukan di Jakarta",
        "Mendikbud Umumkan Perubahan Kurikulum Nasional",
        "RUU Pilkada Serentak Resmi Disahkan DPR",
        "Harga Emas Antam Tembus Rekor Tertinggi",
        "Vaksinasi Booster Ketiga Mulai Didistribusikan",
        "Beasiswa LPDP Buka Kuota 10 Ribu Mahasiswa",
        "Debat Capres Pertama Digelar Bulan Depan",
        "Inflasi Q3 Menurun Signifikan, Rupiah Menguat",
        "Kasus Demam Berdarah Melonjak Jelang Musim Hujan",
        "Syarat Baru Kelulusan Mahasiswa Tanpa Skripsi",
        "KPU Tetapkan Jadwal Kampanye Terbuka"
    )

    private val hooksBerita = listOf(
        "Pemerintah melalui Kementerian Keuangan akhirnya menerbitkan regulasi turunan terkait insentif.",
        "Dinas Kesehatan DKI Jakarta mengonfirmasi adanya temuan 12 kasus varian flu burung jenis baru.",
        "Kementerian Pendidikan, Kebudayaan, Riset, dan Teknologi resmi merilis struktur kurikulum.",
        "Dewan Perwakilan Rakyat (DPR) RI dalam sidang paripurna hari ini secara aklamasi menyetujui.",
        "Harga emas batangan PT Aneka Tambang Tbk (Antam) pada perdagangan pagi ini melonjak tajam.",
        "Kementerian Kesehatan mengumumkan pendistribusian vaksin booster ketiga untuk kelompok rentan.",
        "Pendaftaran beasiswa Lembaga Pengelola Dana Pendidikan (LPDP) tahap kedua tahun ini resmi dibuka.",
        "Komisi Pemilihan Umum (KPU) menetapkan jadwal debat perdana calon presiden akan dilaksanakan.",
        "Badan Pusat Statistik (BPS) merilis data inflasi kuartal ketiga yang menunjukkan tren penurunan.",
        "Kementerian Kesehatan mengeluarkan peringatan dini terkait lonjakan kasus Demam Berdarah Dengue.",
        "Menteri Pendidikan mengeluarkan Permendikbudristek terbaru yang mengatur opsi tugas akhir.",
        "Komisi Pemilihan Umum (KPU) resmi merilis Peraturan KPU terkait jadwal tahapan kampanye."
    )

    private fun kategoriBerita(index: Int): String {
        return when (index) {
            0, 4, 8 -> "Ekonomi"
            1, 5, 9 -> "Kesehatan"
            2, 6, 10 -> "Pendidikan"
            3, 7, 11 -> "Politik"
            else -> "Ekonomi"
        }
    }

    private fun newsSimulator(): Flow<News> = flow {
        for (i in 0 until 12) {
            delay(2000)
            emit(News(i + 1, judulBerita[i], kategoriBerita(i), hooksBerita[i]))
        }
    }

    private val _selectedCategory = MutableStateFlow("Semua")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    fun setCategoryFilter(category: String) {
        _selectedCategory.value = category
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val processedNewsFlow: Flow<List<News>> = _selectedCategory.flatMapLatest { category ->
        newsSimulator()
            .filter { if (category == "Semua") true else it.category == category }
            .scan(emptyList<News>()) { accumulator, value ->
                listOf(value) + accumulator
            }
    }

    fun markAsRead() {
        _readCount.value += 1
    }

    suspend fun fetchNewsDetailAsync(id: Int): String {
        delay(1000)
        return "Bupati Gowa Sitti Husniah Talenrang telah ditetapkan sebagai tersangka korupsi terkait kasus pemerasan dan gratifikasi dalam pengurusan izin proses Persetujuan Bangunan Gedung (PBG) tahun 2025-2026. Sitti Husniah diduga memeras pengelola minimarket di Kabupaten Gowa senilai Rp 850 juta dengan dalih dana corporate social responsibility (CSR).\n\n" +
                "Dirtindak Kortas Tipikor Polri Brigjen Robertus Yohanes De Deo menjelaskan pemerasan terhadap pengelola minimarket tersebut bermula saat pihak pengusaha hendak mengurus izin pembukaan gerai baru di Kabupaten Gowa. Sitti Husniah kemudian memerintahkan pengelola berkoordinasi dengan orang kepercayaannya berinisial HA."
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val viewModel = remember { NewsViewModel() }
    val readCount by viewModel.readCount.collectAsState()
    val newsList by viewModel.processedNewsFlow.collectAsState(initial = emptyList())
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    var selectedDetail by remember { mutableStateOf<String?>(null) }
    var detailTitle by remember { mutableStateOf("") }

    val filterOptions = listOf("Semua", "Politik", "Kesehatan", "Pendidikan", "Ekonomi")

    // Mendefinisikan palet warna Biru-Putih
    val blueWhiteScheme = lightColorScheme(
        primary = Color(0xFF1E88E5), // Warna Biru Utama (Tombol, Ikon, Header)
        onPrimary = Color.White, // Teks di atas warna primary
        primaryContainer = Color(0xFFE3F2FD), // Biru sangat muda (Highlight/Background pelengkap)
        onPrimaryContainer = Color(0xFF0D47A1), // Biru tua (Teks di atas highlight)
        background = Color(0xFFF5F9FF), // Putih kebiruan untuk latar belakang utama
        surface = Color.White, // Putih murni untuk kartu berita
        onSurface = Color.Black // Teks hitam/gelap di atas kartu putih
    )

    MaterialTheme(colorScheme = blueWhiteScheme) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("News Feeds Simulator") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Berita dibaca hari ini: $readCount",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary, // Teks biru
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filterOptions) { category ->
                        FilterChip(
                            selected = selectedCategory == category,
                            onClick = { viewModel.setCategoryFilter(category) },
                            label = { Text(category) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                }

                AnimatedVisibility(visible = selectedDetail != null) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(detailTitle, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(selectedDetail ?: "", style = MaterialTheme.typography.bodyMedium)
                            Button(
                                onClick = { selectedDetail = null },
                                modifier = Modifier.padding(top = 12.dp)
                            ) {
                                Text("Tutup")
                            }
                        }
                    }
                }

                if (newsList.isNotEmpty()) {
                    val headline = newsList.first()
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                            .clickable {
                                viewModel.markAsRead()
                                detailTitle = headline.title
                                selectedDetail = "Memuat detail berita..."
                                coroutineScope.launch {
                                    selectedDetail = viewModel.fetchNewsDetailAsync(headline.id)
                                }
                            },
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Badge(containerColor = MaterialTheme.colorScheme.primary) {
                                Text("Berita Utama", color = Color.White, modifier = Modifier.padding(horizontal = 4.dp))
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = headline.title,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = headline.snippet,
                                style = MaterialTheme.typography.bodyMedium,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }

                    Text(
                        text = "Berita Terbaru Hari Ini",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(newsList.drop(1), key = { it.id }) { news ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                                        Text(
                                            text = news.category,
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.primary // Teks Kategori Biru
                                        )
                                        Text(
                                            text = news.title,
                                            style = MaterialTheme.typography.titleSmall,
                                            fontWeight = FontWeight.Bold,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        Text(
                                            text = news.snippet,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = Color.DarkGray, // Teks deskripsi abu-abu gelap agar kontras dengan latar putih
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                    Button(
                                        onClick = {
                                            viewModel.markAsRead()
                                            detailTitle = news.title
                                            selectedDetail = "Memuat detail berita..."
                                            coroutineScope.launch {
                                                selectedDetail = viewModel.fetchNewsDetailAsync(news.id)
                                            }
                                        },
                                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
                                    ) {
                                        Text("Baca", color = Color.White)
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Sabar ya lagi cari berita...", color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }
    }
}