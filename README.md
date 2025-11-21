# Proyek Automasi Pengujian Web - Swag Labs

Proyek ini merupakan contoh implementasi automasi pengujian fungsional pada situs e-commerce demo "Swag Labs" (saucedemo.com). Proyek ini dibangun menggunakan Java dan dirancang untuk menunjukkan penerapan praktik terbaik dalam rekayasa kualitas (Quality Assurance Engineering), menjadikannya portofolio yang ideal.

## 🎯 Tujuan Proyek

- **Automasi Fungsional:** Mengotomatiskan skenario pengujian utama seperti proses login, manajemen inventaris produk, dan alur kerja keranjang belanja.
- **Best Practice Implementation:** Menerapkan design pattern dan praktik terbaik dalam automasi pengujian, seperti Page Object Model (POM).
- **CI/CD Ready:** Struktur proyek yang siap diintegrasikan dengan pipeline CI/CD untuk eksekusi pengujian secara otomatis.
- **Custom Reporting:** Menghasilkan laporan pengujian yang informatif, termasuk pengambilan screenshot otomatis saat terjadi kegagalan dan pengiriman notifikasi melalui email.

## 🛠️ Teknologi & Alat yang Digunakan

- **Bahasa Pemrograman:** [Java (JDK 11)](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- **Build Automation:** [Apache Maven](https://maven.apache.org/)
- **Framework Automasi Browser:** [Selenium WebDriver](https://www.selenium.dev/)
- **Framework Pengujian:** [TestNG](https://testng.org/)
- **Utilitas:**
  - `commons-io`: Untuk operasi file (digunakan dalam pengambilan screenshot).
  - `jakarta.mail`: Untuk fungsionalitas pengiriman laporan via email.

## ✨ Fitur Utama & Design Pattern

Arsitektur proyek ini dirancang agar modular, mudah dipelihara, dan skalabel.

### 1. Page Object Model (POM)
Proyek ini mengadopsi Page Object Model (POM) untuk memisahkan logika interaksi halaman web dari logika pengujian.
- **`pages`**: Setiap halaman pada aplikasi (misalnya, `LoginPage`, `InventoryPage`) direpresentasikan sebagai sebuah class Java. Class ini berisi locator elemen web dan metode untuk berinteraksi dengan elemen tersebut.
- **`tests`**: Kelas pengujian (misalnya, `LoginTest`, `InventoryTest`) hanya berfokus pada alur pengujian dan validasi (assertion), dengan memanggil metode dari *Page Objects*.

### 2. Data-Driven Testing
Pengujian, seperti pada `LoginTest`, menggunakan anotasi `@Parameters` dari TestNG. Ini memungkinkan data pengujian (contoh: username, password) dipisahkan dari kode dan disuplai melalui file `testng.xml`, sehingga memudahkan pengelolaan kasus uji dengan data yang berbeda-beda.

### 3. TestNG Listeners untuk Pelaporan Kustom
Untuk meningkatkan kualitas pelaporan, proyek ini mengimplementasikan TestNG Listeners:
- **`ScreenshotListener`**: Secara otomatis mengambil screenshot dari halaman web setiap kali sebuah tes gagal. Screenshot ini sangat berharga untuk proses debugging.
- **`SendEmailReporter`**: Setelah seluruh suite pengujian selesai dieksekusi, listener ini akan secara otomatis mengirimkan ringkasan hasil pengujian ke alamat email yang telah dikonfigurasi.

### 4. Struktur Basis Test yang Terpusat (`BaseTest`)
Semua kelas pengujian mewarisi (`extends`) dari kelas `BaseTest`. Kelas ini bertanggung jawab untuk:
- **Setup & Teardown Driver**: Menginisialisasi dan menutup instance `WebDriver` sebelum dan sesudah setiap metode tes.
- **Konfigurasi Driver**: Mengatur `ChromeOptions` untuk menonaktifkan pop-up yang mengganggu dan mengoptimalkan lingkungan pengujian.
- **Manajemen Konteks**: Membagikan instance `WebDriver` ke *listeners* agar dapat digunakan saat mengambil screenshot.

## 🧪 Skenario Pengujian yang Dicakup

Proyek ini mencakup beberapa skenario pengujian fungsional, antara lain:
- **Login:**
  - Login berhasil dengan kredensial yang valid.
  - Kegagalan login dengan berbagai kombinasi data tidak valid (username salah, password salah, input kosong).
- **Inventaris Produk:**
  - Menambahkan satu atau beberapa produk ke keranjang belanja.
  - Mengurutkan produk berdasarkan harga (rendah ke tinggi).
  - Menghapus produk dari keranjang.
  - Reset state aplikasi setelah produk ditambahkan.
- **Akses Halaman:**
  - Verifikasi bahwa halaman inventaris tidak dapat diakses tanpa login terlebih dahulu.

## 🚀 Cara Menjalankan Proyek

1.  **Prasyarat:**
    - Pastikan **Java JDK 11** terinstal di sistem Anda.
    - Pastikan **Apache Maven** terinstal dan terkonfigurasi di `PATH` sistem Anda.
    - Pastikan **Google Chrome** terinstal. `ChromeDriver` akan diunduh dan dikelola secara otomatis oleh Selenium Manager.

2.  **Konfigurasi (Opsional):**
    - Untuk fungsionalitas pengiriman email, konfigurasikan detail SMTP server, username, dan password Anda di dalam file `src/test/resources/mailtrap.properties`.

3.  **Eksekusi Tes via Maven:**
    Buka terminal atau command prompt, arahkan ke direktori root proyek, dan jalankan perintah berikut:
    ```bash
    mvn clean test
    ```
    Maven akan secara otomatis mengompilasi kode, menjalankan semua pengujian yang didefinisikan dalam `testng.xml`, dan menghasilkan laporan.

## 📊 Laporan Hasil Pengujian

Setelah eksekusi selesai, laporan pengujian dapat ditemukan di direktori `target/surefire-reports/`.
- **`emailable-report.html`**: Laporan HTML ringkas yang dapat dibuka di browser.
- **`index.html`**: Laporan TestNG HTML yang lebih detail.

Screenshot yang diambil saat tes gagal akan disimpan di direktori `target/screenshots/`.
