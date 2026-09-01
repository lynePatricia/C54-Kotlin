package com.lyne.annexe1b

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val nombre = calculerNbLignes()
        println(nombre)
    }
    fun calculerNbLignes() : Int{
        val a = ArrayList<String>()

        try {
            val fis = openFileInput("fichier.txt")
            val isr = InputStreamReader(fis)
            val br = BufferedReader(isr)

            br.forEachLine { ligne -> a.add(ligne) }

        }catch(e : FileNotFoundException)
        {
            val toast = Toast.makeText(this, "il n'y a pas de mémo", Toast.LENGTH_LONG)
            toast.show()
            finish()
        }
        return a.size
    }

    //autre facon
    fun nbLignes(): Int{
        val fis = openFileInput("fichier.txt")
        val isr = InputStreamReader(fis)
        val br = BufferedReader(isr)
        var compteur = 0;

        br.use{
            for (line in br.lines())
                compteur++
        }
        return compteur
    }

    fun calculerNbreChar(): Int{
        val fis = openFileInput("fichier.txt")
        val isr = InputStreamReader(fis)
        val br = BufferedReader(isr)
        var compteurChar =0

        br.use{
            br.forEachLine {compteurChar += it.length}

        }
        return compteurChar
    }


}