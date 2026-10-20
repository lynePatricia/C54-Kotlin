package com.lyne.tp1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    //var player: ExoPlayer?= null
    lateinit var onPopMusic: ImageButton
    lateinit var onRnb: ImageButton
    lateinit var onJazz: ImageButton
    lateinit var style : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        onPopMusic = findViewById(R.id.popButton)
        onRnb = findViewById(R.id.rnbButton)
        onJazz = findViewById(R.id.jazzButton)

        val ec = Ecouteur()

        onPopMusic.setOnClickListener(ec)
        onRnb.setOnClickListener(ec)
        onJazz.setOnClickListener(ec)

//        vue = findViewById(R.id.vue)
//        player = ExoPlayer.Builder(this).build()
//        vue.setUseController(false)

    }
    inner class Ecouteur : View.OnClickListener{
        override fun onClick(v : View?){
            val i = Intent(this@MainActivity, AffichageActivity::class.java)
            if(v == onPopMusic)
                style = "POP"
            else if(v == onRnb)
                style = "RNB"
            else
                style = "JAZZ"

            i.putExtra("style", style)
            startActivity(i)
        }
    }
//    override fun onStart() {
//        super.onStart()
//        vue.player = player
//        val mp3url = ""
//        val mediaItem = MediaItem.fromUri(mp3url)
//        player?.addMediaItem(mediaItem)
//        player?.prepare()
//        player?.play()
//    }
}