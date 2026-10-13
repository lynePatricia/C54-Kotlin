package com.lyne.tp1

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class SimpleAdapterActivity : AppCompatActivity() {
    lateinit var liste : ListView

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
        var lien = ""
        val style = intent.getStringExtra("style")

        if(style == "POP")
            lien = "https://itunes.apple.com/search?term=Dua+Lipa&entity=song&limit=3"
        else if(style == "RNB")
            lien = "https://itunes.apple.com/search?term=The+Weeknd&entity=song&limit=3"
        else
            lien = "https://itunes.apple.com/search?term=Miles+Davis&entity=song&limit=3"

        val mod : Modele
        //mod.lireJSON(this, lien)

//        val adapteur = SimpleAdapter(this,
//            mod.lireJSON(this, lien),
//            R.layout.item,
//            arrayOf("previewUrl", "trackName", "artistName"),
//            arr)



    }
}