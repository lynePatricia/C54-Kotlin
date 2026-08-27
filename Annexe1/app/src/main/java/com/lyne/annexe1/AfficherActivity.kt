package com.lyne.annexe1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.InputStreamReader

class AfficherActivity : AppCompatActivity() {
    lateinit var liste: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_afficher)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        liste = findViewById(R.id.listeMemo)

        liste.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lireMemos())
    }

    fun lireMemos(): ArrayList<String>
    {
        val fis = openFileInput("fichier.txt")
        val isr = InputStreamReader(fis)

        val br = BufferedReader(isr)

        val a = ArrayList<String>()

        br.use{
            br.forEachLine { ligne -> a.add(ligne) }

            //a = br.readLines() as ArrayList<String> //as utilisé pour transtypé
        }
        return a
    }
}
