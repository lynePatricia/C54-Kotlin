package com.lyne.tp1

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.bumptech.glide.Glide

class LectureActivity : AppCompatActivity(), ObservateurMusique {
    lateinit var imgCover: ImageView
    lateinit var nomMusique: TextView
    lateinit var timer : TextView
    lateinit var onPrev: ImageButton
    lateinit var onPlay : ImageButton
    lateinit var onNext : ImageButton
    lateinit var seekBar: SeekBar
    var position : Int = 0
    private var listes: ArrayList<HashMap<String, Any>>? = null
//    val listes = intent.getSerializableExtra("liste") as? ArrayList<HashMap<String, Any>>
//    lateinit var ec : Ecouteur
    var player: ExoPlayer?= null
    lateinit var adapteur: AffichageActivity.Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lecture)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        listes = intent.getSerializableExtra("liste") as? ArrayList<HashMap<String, Any>>

        imgCover = findViewById(R.id.vue)
        nomMusique = findViewById(R.id.name)
        timer = findViewById(R.id.timer)

        onPrev = findViewById(R.id.previousButton)
        onPlay = findViewById(R.id.play)
        onNext = findViewById(R.id.next)
        seekBar = findViewById(R.id.seekBar)

        player = ExoPlayer.Builder(this).build()


        onPrev.setOnClickListener {
            if(player?.hasNextMediaItem() == true){
                position--
                player?.seekToPreviousMediaItem()

                Glide.with(this)
                    .load(listes?.get(position)?.get("cover").toString())
                    .override(300, 300)
                    .into(imgCover)
                nomMusique.text = listes?.get(position)?.get("titre").toString() + "\n" + listes?.get(position)
                    ?.get("artiste")
                    .toString()
            }
        }

        onPlay.setOnClickListener {
            if(player?.isPlaying == true)
                player?.pause()
            else
                player?.play()
        }

        onNext.setOnClickListener {
            if(player?.hasNextMediaItem() == true){
                position++
                player?.seekToNextMediaItem()

                Glide.with(this)
                    .load(listes?.get(position)?.get("cover").toString())
                    .override(300, 300)
                    .into(imgCover)
                nomMusique.text = listes?.get(position)?.get("titre").toString() + "\n" + listes?.get(position)
                    ?.get("artiste")
                    .toString()
            }
        }

    }
    override fun onStart() {
        super.onStart()
        val currentListes = listes ?: return

        val posDepart = intent.getIntExtra("position", 0)
        for(elem in currentListes!!){
            val url = elem.get("extrait").toString()

            println("ma musique " + url)
            val mediaItem = MediaItem.fromUri(url)
            player?.addMediaItem(mediaItem)
        }
        player?.seekTo(posDepart, 0)

        Glide.with(this)
            .load(currentListes.get(posDepart).get("cover").toString())
            .override(300, 300)
            .into(imgCover)
        position = posDepart
        nomMusique.text = currentListes.get(posDepart).get("titre").toString() + "\n" + currentListes.get(posDepart).get("artiste").toString()

        player?.prepare()
        player?.play()
    }
//    inner class Ecouteur{
//
//    }

    override fun actualiser(l: ListeMusique) {
    }
}