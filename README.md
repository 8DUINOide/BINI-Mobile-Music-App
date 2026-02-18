# <img src="app/src/main/res/drawable/logo.jpg" alt="App Icon" width="40" style="vertical-align: bottom;"/> BINI Mobile Music App

A mobile music streaming application dedicated to the F-Pop girl group **BINI**. This app replicates the core functionality of Spotify, offering a seamless listening experience with a focus on BINI's discography.

## 📱 Try it Out (Demo)
**Want to test the app?**
An APK file is included directly in this repository for your convenience.
-   **File:** `BINI-Mobile-Music-App-v1.0.apk` (Located in the main folder)
-   **How to Install:** Download the file to your Android phone and install it (enable "Install from Unknown Sources" if prompted).

## Features

-   **Music Playback:** High-quality music streaming using **ExoPlayer**.
-   **Spotify-like Controls:**
    -   **Auto-play:** Automatically plays the next song in the playlist when the current one finishes.
    -   **Playlist Navigation:** "Next" and "Previous" buttons to skip tracks within categories or albums.
    -   **Background Playback:** Audio continues playing even when navigating through the app.
-   **Smart UI:**
    -   Dynamic "Now Playing" screen that updates title, artist, and cover art instantly.
    -   Animated GIF support for a lively playback experience.
-   **Cloud Integration:** Fetches song metadata and audio URLs from **Firebase Firestore**.
-   **User Authentication:** Secure login and signup via Firebase Auth.

## Tech Stack

-   **Language:** Kotlin
-   **Minimum SDK:** 24 (Android 7.0 Nougat)
-   **Target SDK:** 34 (Android 14)
-   **Architecture:** MVVM / Clean Architecture principles
-   **Dependencies:**
    -   **ExoPlayer (Media3):** For robust media playback.
    -   **Firebase Firestore:** NoSQL cloud database for song data.
    -   **Firebase Auth:** User management and authentication.
    -   **Glide:** Efficient image loading and caching.
    -   **ViewBinding:** Safer and faster interaction with XML layouts.

## Getting Started

### Prerequisites

-   Android Studio Iguana or later.
-   Top-level `google-services.json` file (you must provide your own Firebase project configuration).

### Installation

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/your-username/BINI-Mobile-Music-App.git
    ```
2.  **Open in Android Studio:**
    -   Launch Android Studio and select "Open".
    -   Navigate to the cloned directory.
3.  **Firebase Setup:**
    -   Create a project in the [Firebase Console](https://console.firebase.google.com/).
    -   Add an Android app to your Firebase project (package name: `com.example.bini`).
    -   Download the `google-services.json` file.
    -   Place it in the `app/` directory of the project.
    -   Enable **Authentication** (Email/Password) and **Firestore Database** in the Firebase Console.
4.  **Build and Run:**
    -   Connect an Android device or start an emulator.
    -   Click the "Run" button (green play icon) in Android Studio.

## Usage

1.  **Sign Up/Login:** Create an account to access the app.
2.  **Browse Songs:** Explore different categories or albums of BINI songs.
3.  **Play Music:** Tap on a song to start listening.
4.  **Control Playback:**
    -   Use the player controls at the bottom or on the dedicated player screen.
    -   Use the **Next (>>)** button to skip to the next track.
    -   Use the **Previous (<<)** button to go back.
    -   Let the song finish to seemingly flow into the next track.

## License

This project is for educational and personal portfolio purposes. All music and images related to BINI are property of their respective owners (ABS-CBN Star Music).

## Acknowledgments

-   Inspired by Spotify's UI/UX.
-   Dedicated to BINI and visual/audio assets used for demonstration.
