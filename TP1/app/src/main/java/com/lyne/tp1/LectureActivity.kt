package com.lyne.tp1

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.bumptech.glide.Glide
import java.io.Serializable

class LectureActivity : AppCompatActivity(), ObservateurMusique {
    lateinit var imgCover: ImageView
    lateinit var nomMusique: TextView
    lateinit var timer : TextView
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

        imgCover = findViewById(R.id.vue)
        nomMusique = findViewById(R.id.name)
        timer = findViewById(R.id.timer)
        player = ExoPlayer.Builder(this).build()


    }
    override fun onStart() {
        super.onStart()

        val listes = intent.getSerializableExtra("liste") as? ArrayList<HashMap<String, Any>>
        val posDepart = intent.getIntExtra("position", 0)

        for(elem in listes!!){
            val url = elem.get("extrait").toString()
            val cover = elem.get("cover").toString()
            val titre = elem.get("titre").toString()
            val artiste = elem.get("artiste").toString()

            println("ma musique " + url)
            val mediaItem = MediaItem.fromUri(url)
            player?.addMediaItem(mediaItem)
        }
        player?.seekTo(posDepart, 0)

        Glide.with(this)
            .load(listes.get(posDepart).get("cover").toString())
            .override(300, 300)
            .into(imgCover)
        nomMusique.text = listes.get(posDepart).get("titre").toString() + "\n" +
                listes.get(posDepart).get("artiste").toString()


        player?.prepare()
        player?.play()
    }

    override fun actualiser(l: ListeMusique) {
    }
}