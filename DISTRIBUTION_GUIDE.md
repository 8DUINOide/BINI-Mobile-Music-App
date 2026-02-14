# How to Share Your App (APK Distribution)

Since you are building a native Android app with Android Studio, you don't need Expo Go. You can share your app directly by generating an **APK file**. Users can download this file and install it on their phones.

## Method: Generate a Debug APK (Easiest)
This method is best for testing with friends or a small group.

1.  Open your project in **Android Studio**.
2.  Go to the top menu and click **Build** -> **Build Bundle(s) / APK(s)** -> **Build APK(s)**.
3.  Wait for the build to finish. A notification will appear at the bottom right saying "Build APK(s): APK(s) generated successfully".
4.  Click **locate** in that notification, or navigate to:
    `[Your Project Folder]\app\build\outputs\apk\debug\`
5.  You will see a file named `app-debug.apk`.
6.  **Rename** this file to something nicer, like `BINI-App-v1.0.apk`.
7.  **Share this file** via Google Drive, Telegram, Messenger, or Email.

## How Users Install It
Since this app is not from the Play Store, users need to allow installation from unknown sources:

1.  Download the APK file you sent.
2.  Tap to open it.
3.  A warning will pop up: "For your security, your phone is not allowed to install unknown apps from this source."
4.  Tap **Settings** on that prompt.
5.  Toggle **Allow from this source** (or "Install unknown apps").
6.  Go back and tap **Install**.

## Important Note on Firebase & Signatures
Since your app uses Firebase (Auth and Firestore), it relies on a specific "fingerprint" (SHA-1) from your computer to allow login.

If you share the **Debug APK** (generated above), it uses your computer's debug key.
-   **It SHOULD work** provided the key hasn't changed.
-   **If users cannot log in**, you may need to add the SHA-1 of the key you used to sign the APK to your Firebase Console settings.

### Production Build (Signed APK)
If you want a more official "Release" version:
1.  Go to **Build** -> **Generate Signed Bundle / APK**.
2.  Choose **APK**.
3.  Create a new **Key store path** (keep this file safe!).
4.  Fill in the passwords and details.
5.  Select **release** build variant.
6.  **CRITICAL:** You must get the SHA-1 of this new Key store and add it to your Firebase Console > Project Settings, otherwise **Google Login/Auth will fail** on the release version.
