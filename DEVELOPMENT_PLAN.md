# SilentPad - Development Plan

## Overview
SilentPad adalah aplikasi notepad dengan fitur autentikasi dan deteksi lokasi. Aplikasi ini dibangun menggunakan Jetpack Compose dan Room Database untuk penyimpanan lokal.

## Fitur Utama

### 1. Splash Screen
- Menampilkan logo aplikasi "SilentPad"
- Deteksi lokasi pengguna secara otomatis
- Animasi transisi yang smooth
- Navigasi ke Welcome Screen setelah lokasi terdeteksi

### 2. Welcome Screen
- Background dengan gambar wolf (wolf_647528_1920.png)
- Menampilkan lokasi yang terdeteksi
- Tombol LOGIN dan Sign Up
- Design sesuai template UI/UX

### 3. Login Screen
- Background dengan gambar wolf
- Form email dan password
- Fitur show/hide password
- Link "Forgot Password"
- Tombol social login (Google, Facebook) - placeholder
- Link ke Register Screen

### 4. Register Screen
- Background dengan gambar wolf
- Form email, password, dan confirm password
- Validasi password match
- Tombol social sign up (Google, Facebook) - placeholder
- Link ke Login Screen

### 5. Main Activity (Notepad)
- List semua notes yang tersimpan
- Floating Action Button untuk membuat note baru
- Edit dan delete note
- Penyimpanan menggunakan Room Database
- Dark theme sesuai design

## Tech Stack

- **UI Framework**: Jetpack Compose
- **Database**: Room Database (SQLite)
- **Location Services**: Google Play Services Location
- **Architecture**: MVVM dengan ViewModel
- **Language**: Kotlin

## Color Palette

- Light Blue: `#A9CFFF`
- Darker Blue: `#3F6694`
- White: `#FFFFFF`
- Black: `#000514`

## Font Family

- Wellfleet (untuk display text)
- Yatra One (untuk body text)
- *Note: Saat ini menggunakan default font, dapat diganti jika file font tersedia*

## Project Structure

```
app/src/main/java/com/example/silentpad/
├── data/
│   ├── Note.kt              # Entity untuk note
│   ├── NoteDao.kt          # Data Access Object
│   └── NoteDatabase.kt     # Room Database
├── ui/
│   └── theme/
│       ├── Color.kt        # Color palette
│       ├── Theme.kt        # Material Theme
│       └── Type.kt         # Typography
├── SplashActivity.kt       # Entry point dengan deteksi lokasi
├── WelcomeActivity.kt      # Welcome screen dengan tombol login/signup
├── LoginActivity.kt       # Login screen
├── RegisterActivity.kt    # Register screen
└── MainActivity.kt         # Notepad main screen
```

## Flow Aplikasi

1. **SplashActivity** (Entry Point)
   - Logo animation
   - Deteksi lokasi
   - Navigate ke WelcomeActivity

2. **WelcomeActivity**
   - Tampilkan lokasi
   - Pilih Login atau Sign Up

3. **LoginActivity / RegisterActivity**
   - Autentikasi user
   - Navigate ke MainActivity

4. **MainActivity**
   - Tampilkan list notes
   - Create, Edit, Delete notes
   - Simpan ke local storage (Room Database)

## Dependencies

- Jetpack Compose BOM
- Material3
- Room Database
- Google Play Services Location
- Lifecycle ViewModel

## Permissions

- `ACCESS_FINE_LOCATION` - Untuk deteksi lokasi akurat
- `ACCESS_COARSE_LOCATION` - Untuk deteksi lokasi umum
- `INTERNET` - Untuk geocoding lokasi

## Next Steps (Future Enhancements)

1. Implementasi autentikasi real (Firebase Auth atau backend API)
2. Implementasi social login (Google, Facebook)
3. Fitur forgot password
4. Search notes
5. Categories/Tags untuk notes
6. Export notes (PDF, Text)
7. Cloud sync (opsional)
8. Custom font Wellfleet dan Yatra One

## Catatan

- Background images sudah tersedia di `res/drawable/`
- Design mengikuti template UI/UX yang diberikan
- Semua data notes disimpan secara lokal di device
- Lokasi digunakan untuk informasi saja, tidak disimpan ke database


