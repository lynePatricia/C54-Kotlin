package com.lyne.examen1

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Scanner

class MainActivity : AppCompatActivity() {
    lateinit var liste : Spinner
    var lanceur: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        liste = findViewById(R.id.spinner)
        liste.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lireFichier())


        lanceur = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            CallBackSpinner()
        )
//        liste.setOnClickListener{
//            //garde en mémoire la valeur sélectionnée
//            val intent = Intent()
//            lanceur?.launch(intent)
//        }

    }
    fun lireFichier(): ArrayList<String>{
        val a = ArrayList<String>()

        try {
            val fis = openFileInput("pl.txt")
            val isr = InputStreamReader(fis)
            val br = BufferedReader(isr)

            br.forEachLine {
                ligne -> a.add(ligne)
            }
//            val fis = getResources().openRawResource(R.raw.pl)
//            val s = Scanner(fis)
//            while(s.hasNext()){
//                val nom = s.next()
//                a.add(nom)
//                if(s.hasNextInt()){
//                    val nbre = s.nextInt()
//                }
//            }

        } catch (e: Exception) {
            Toast.makeText(this, "Fichier Introuvable", LENGTH_LONG).show()
        }
        return a
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //outState.putSerializable("util", util)
    }

    inner class CallBackSpinner : ActivityResultCallback<ActivityResult>{
        override fun onActivityResult(result: ActivityResult) {
            if(result.resultCode == RESULT_OK){
                var intent = result.data
                var uri = intent!!.data
                liste.adapter = uri as SpinnerAdapter?

            }
        }

    }
}