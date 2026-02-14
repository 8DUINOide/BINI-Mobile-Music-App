package com.example.bini

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.bini.models.SongModel
import com.google.firebase.firestore.FirebaseFirestore

object MyExoplayer {
    private var exoPlayer: ExoPlayer? = null
    private var currentSong: SongModel? = null
    private var songsList: List<String> = emptyList()
    private var currentSongIndex: Int = -1
    private var songChangeListener: ((SongModel) -> Unit)? = null

    fun setSongActivityListener(listener: (SongModel) -> Unit) {
        songChangeListener = listener
    }

    fun getCurrentSong(): SongModel? {
        return currentSong
    }

    fun getInstance(): ExoPlayer? {
        return exoPlayer
    }

    fun startPlaying(context: Context, song: SongModel, index: Int, songIds: List<String>) {
        if (exoPlayer == null)
            exoPlayer = ExoPlayer.Builder(context).build()

        if (currentSong != song) {
            // It's a new song so start playing
            currentSong = song
            currentSongIndex = index
            songsList = songIds
            updateCount()
            songChangeListener?.invoke(song)
            currentSong?.url?.apply {
                val mediaItem = MediaItem.fromUri(this)
                exoPlayer?.setMediaItem(mediaItem)
                exoPlayer?.prepare()
                exoPlayer?.play()
            }
        }
    }

    fun playNext(context: Context) {
        if (songsList.isNotEmpty() && currentSongIndex < songsList.size - 1) {
            currentSongIndex++
            startPlayingFromIndex(context)
        }
    }

    fun playPrevious(context: Context) {
        if (songsList.isNotEmpty() && currentSongIndex > 0) {
            currentSongIndex--
            startPlayingFromIndex(context)
        }
    }

    private fun startPlayingFromIndex(context: Context) {
        val songId = songsList[currentSongIndex]
        FirebaseFirestore.getInstance().collection("songs")
            .document(songId).get()
            .addOnSuccessListener {
                val song = it.toObject(SongModel::class.java)
                song?.let { newSong ->
                    currentSong = newSong
                    updateCount()
                    songChangeListener?.invoke(newSong)
                    newSong.url.apply {
                        val mediaItem = MediaItem.fromUri(this)
                        exoPlayer?.setMediaItem(mediaItem)
                        exoPlayer?.prepare()
                        exoPlayer?.play()
                    }
                }
            }
    }

    fun updateCount() {
        currentSong?.id?.let { id ->
            FirebaseFirestore.getInstance().collection("songs")
                .document(id)
                .get().addOnSuccessListener {
                    var latestCount = it.getLong("count")
                    if (latestCount == null) {
                        latestCount = 1L
                    } else {
                        latestCount = latestCount + 1
                    }

                    FirebaseFirestore.getInstance().collection("songs")
                        .document(id)
                        .update(mapOf("count" to latestCount))
                }
        }
    }
}