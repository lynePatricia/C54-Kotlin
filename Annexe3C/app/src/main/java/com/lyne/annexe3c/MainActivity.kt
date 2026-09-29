package com.lyne.annexe3c

import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText

import android.widget.LinearLayout
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.ObjectInput
import java.io.ObjectInputStream
import java.io.ObjectOutputStream


class MainActivity : AppCompatActivity() {

    lateinit var dent1: LinearLayout
    lateinit var dent2: LinearLayout

    var dent: DentInfo? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dent1 = findViewById(R.id.dent1)
        dent2 = findViewById(R.id.dent2)


        //DÉSÉRIALISE
        try {
            val fis = openFileInput("fichier.ser")
            val ois = ObjectInputStream(fis)
            ois.use{
                val objetDent = ois.readObject() as DentInfo
                (dent1.getChildAt(0) as EditText).setText(objetDent.numero.toString())
                (dent1.getChildAt(1) as CheckBox).isChecked = objetDent.traitement
                (dent1.getChildAt(2) as EditText).setText(objetDent.notes.toString())
            }
        } catch (e: Exception) {
            Toast.makeText(this, "1ère Utilisation", LENGTH_LONG).show()
        }
    }

    override fun onStop() {
        super.onStop()


        val fos = openFileOutput("fichier.ser", MODE_PRIVATE)
        val oos = ObjectOutputStream(fos)
        oos.use{
            val num = (dent1.getChildAt(0) as EditText).text.toString().toInt()
            val etat = (dent1.getChildAt(1) as CheckBox).isChecked
            val note = (dent1.getChildAt(2) as EditText).text.toString()
            val fos = openFileOutput("fichier.ser",MODE_PRIVATE)
            val oos = ObjectOutputStream(fos)
            oos.use {
                oos.writeObject(DentInfo(num,etat,note) )
            }

        }
    }


}