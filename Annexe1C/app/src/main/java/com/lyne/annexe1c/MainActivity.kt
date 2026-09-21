package com.lyne.annexe1c

import android.os.Bundle
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Scanner
import android.util.Log
import android.widget.ArrayAdapter

class MainActivity : AppCompatActivity() {
    lateinit var nbreLignes: TextView
    lateinit var liste: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        nbreLignes = findViewById(R.id.nbreLignes)
        liste = findViewById(R.id.listePlanetes)

        val listePlanetes = chargerPlanetes()

        nbreLignes.text = "Votre fichier compte : ${listePlanetes.size} lignes"
        if (listePlanetes.size > 3) {
            Log.i("test", listePlanetes.elementAt(3).nom)
        }

        liste.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listePlanetes)
    }

    //optimisation pour lire le fichier une seule fois éviter la redondance
    fun chargerPlanetes(): ArrayList<Planete> {
        val v = ArrayList<Planete>()
        try {
            val fis = getResources().openRawResource(R.raw.fichierplanete)
            val s = Scanner(fis)
            while (s.hasNext()) {
                val nom = s.next()
                if (s.hasNextInt()) {
                    val nbSatellites = s.nextInt()
                    v.add(Planete(nom, nbSatellites))
                }
            }
        } catch (e: FileNotFoundException) {
            Toast.makeText(this, "Il n'y a pas de fichier raw", Toast.LENGTH_LONG).show()
        }
        return v
    }

    fun annexe1C():Int{
        val v = ArrayList<Planete>()
        var total: Int = 0
        try {
            val fis = getResources().openRawResource(R.raw.fichierplanete)
            val s = Scanner(fis)

            while(s.hasNext()){
                var temp = Planete(s.next(), s.nextInt())
                v.add(temp)
                total += temp.nbSatellites
            }
            Log.i("test", v.elementAt(3).nom)
        } catch (e: FileNotFoundException) {
            Toast.makeText(this, "il n'y a pas de fichier raw", Toast.LENGTH_LONG).show()
        }

        return v.size
    }
    fun afficherListe(): ArrayList<Planete>{
        val a = ArrayList<Planete>()

        try {
            val fis = getResources().openRawResource(R.raw.fichierplanete)
            val s = Scanner(fis)
            while (s.hasNext()){
                var temp = Planete(s.next(), s.nextInt())
                a.add(temp)
            }
        } catch (e: FileNotFoundException) {
            Toast.makeText(this, "il n'y a pas de fichier raw", Toast.LENGTH_LONG).show()
        }
        return a
    }
}