package com.lyne.tp1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class AffichageActivity : AppCompatActivity(), ObservateurMusique {
    lateinit var liste : ListView
    var modele = Modele
    val remplir = ArrayList<HashMap<String, Any>>()
    lateinit var adapteur: Adapter
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
            lien = "https://itunes.apple.com/search?term=Contemporary+Pop&entity=song&limit=15"
        else if(style == "RNB")
            lien = "https://itunes.apple.com/search?term=RNB+hits&entity=song&limit=15"
        else
            lien = "https://itunes.apple.com/search?term=Jazz+House&entity=song&limit=15"

        modele.lireJSON(this, lien)

    }

    override fun onStart() {
        super.onStart()
        modele.ajouterObservateur(this)
    }
    override fun onStop() {
        modele.retirerObservateur(this)

        super.onStop()
    }

    override fun actualiser(listes: ListeMusique) {
        remplir.clear()
        //convertir liste des musiques en arrayList de hashmap
        for (i in listes.results){
            val temp = HashMap<String, Any>()
            temp.put("titre", i.trackName)
            temp.put("artiste", i.artistName)
            temp.put("cover", i.artworkUrl100)
            temp.put("extrait", i.previewUrl)

            remplir.add(temp)
            println("liste = " + listes.results.size)
        }
        adapteur = Adapter(this,
            remplir,
            R.layout.item,
            arrayOf("titre", "artiste", "cover"), // Clés correspondantes
            intArrayOf(R.id.idTitre, R.id.idArtist, R.id.idCover)
        )
        liste.adapter = adapteur
        liste.setOnItemClickListener{_, _, position, _ ->
            Toast.makeText(this, remplir.get(position).get("titre").toString(), LENGTH_LONG).show()
            val i = Intent(this@AffichageActivity, LectureActivity::class.java)
            i.putExtra("liste", remplir)
            i.putExtra("position", position)
            startActivity(i)
        }

    }

    inner class Adapter (context: Context,
                         data: ArrayList<HashMap<String, Any>>,
                         resource: Int,
                         from: Array<String>,
                         to: IntArray) : SimpleAdapter(context, data, resource, from, to){

        override open fun setViewImage(v: ImageView, value: String){
            Glide.with(v.context)
                .load(value)
                .into(v)
        }
    }
}