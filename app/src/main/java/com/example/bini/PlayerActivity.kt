package com.example.bini

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.bumptech.glide.Glide
import com.example.bini.databinding.ActivityPlayerBinding
import com.google.common.util.concurrent.MoreExecutors

class PlayerActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlayerBinding
    lateinit var exoPlayer: ExoPlayer

    var playerListener = object : Player.Listener {
        override fun onIsPlayingChanged(isPlaying: Boolean) {
            super.onIsPlayingChanged(isPlaying)
            showGif(isPlaying)
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            super.onPlaybackStateChanged(playbackState)
            if (playbackState == Player.STATE_ENDED) {
                MyExoplayer.playNext(this@PlayerActivity)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MyExoplayer.getCurrentSong()?.apply {
            updateUi(this.title, this.subtitle, this.coverUrl)
            
            exoPlayer = MyExoplayer.getInstance()!!

            val forwardingPlayer = object : androidx.media3.common.ForwardingPlayer(exoPlayer) {
                override fun isCommandAvailable(command: Int): Boolean {
                    return when (command) {
                        Player.COMMAND_SEEK_TO_NEXT, Player.COMMAND_SEEK_TO_PREVIOUS -> true
                        else -> super.isCommandAvailable(command)
                    }
                }

                override fun getAvailableCommands(): Player.Commands {
                    return super.getAvailableCommands()
                        .buildUpon()
                        .add(Player.COMMAND_SEEK_TO_NEXT)
                        .add(Player.COMMAND_SEEK_TO_PREVIOUS)
                        .build()
                }

                override fun seekToNext() {
                    MyExoplayer.playNext(this@PlayerActivity)
                }

                override fun seekToPrevious() {
                    MyExoplayer.playPrevious(this@PlayerActivity)
                }
                
                override fun hasNextMediaItem(): Boolean {
                    return true
                }

                override fun hasPreviousMediaItem(): Boolean {
                    return true
                }
            }

            binding.playerView.player = forwardingPlayer
            binding.playerView.showController()
            forwardingPlayer.addListener(playerListener)
        }
        
        MyExoplayer.setSongActivityListener { song ->
            updateUi(song.title, song.subtitle, song.coverUrl)
        }
    }

    fun updateUi(title: String, subtitle: String, coverUrl: String) {
        binding.songTitleTextView.text = title
        binding.songSubtitleTextView.text = subtitle
        Glide.with(binding.songCoverImageView).load(coverUrl)
            .circleCrop()
            .into(binding.songCoverImageView)
        Glide.with(binding.songGifImageView).load(R.drawable.media_playing)
            .circleCrop()
            .into(binding.songGifImageView)
    }

    override fun onDestroy() {
        super.onDestroy()
        // No need to manually remove listener from singleton here as we attached to the wrapper which is local.
        // But if we attached to exoPlayer directly in previous code, we should clean up.
        // In the new code, we attach to forwardingPlayer. 
        // We can leave this empty or remove the listener if we kept a reference.
    }

    fun showGif(show: Boolean) {
        if (show)
            binding.songGifImageView.visibility = View.VISIBLE
        else
            binding.songGifImageView.visibility = View.INVISIBLE
    }
}
