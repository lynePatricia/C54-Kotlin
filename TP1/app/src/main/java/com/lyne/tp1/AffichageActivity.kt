package com.lyne.tp1

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class AffichageActivity : AppCompatActivity(), ObservateurMusique {
    lateinit var liste : ListView
    var modele = Modele
    lateinit var chansons : List<Musique>
    val remplir = ArrayList<HashMap<String, Any>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_simple_adapter)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        liste = findViewById(R.id.listeView)

        val style = intent.getStringExtra("style")
        var lien = ""
        if(style == "POP")
            lien = "https://itunes.apple.com/search?term=Dua+Lipa&entity=song&limit=7"
        else if(style == "RNB")
            lien = "https://itunes.apple.com/search?term=The+Weeknd&entity=song&limit=7"
        else
            lien = "https://itunes.apple.com/search?term=Miles+Davis&entity=song&limit=7"

        print(modele.lireJSON(this, lien))
        chansons = modele.lireJSON(this, lien)


//        val adapteur = Adapter(this,
//            k,
//            R.layout.item,
//            arrayOf("previewUrl", "trackName", "artistName"),
//            arr)

    }

    override fun actualiser(listes: ListeMusique) {

        //convertir liste des musiques en arrayList de hasmap
        for (i in listes.musiques){

            val temp = HashMap<String, Any>()
            temp.put("titre", i.trackName)
            temp.put("artiste", i.artistName)
            temp.put("cover", i.artWorkUrl100)
            temp.put("extrait", i.previewUrl)

            remplir.add(temp)
        }
    }

    inner class Adapter (context: Context,
                         data: ArrayList<HashMap<String, Any>>,
                         resource: Int,
                         from: Array<String>,
                         to: IntArray) : SimpleAdapter(context, data, resource, from, to){

        override open fun setViewImage(
            v: ImageView,
            value: String
        ): Unit{
            Glide.with(v.context)
                .load(value)
                .into(v)
        }

    }
}