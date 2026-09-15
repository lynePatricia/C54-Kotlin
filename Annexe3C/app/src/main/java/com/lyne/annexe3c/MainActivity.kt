package com.lyne.annexe3c

import android.os.Bundle

import android.widget.LinearLayout

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
        var numero : Int =  dent1.getChildAt(0) as Int



    }

    override fun onStop() {
        super.onStop()

        val fos = openFileOutput("fichier.ser", MODE_PRIVATE)
        val oos = ObjectOutputStream(fos)
        oos.use{
            oos.writeObject(DentInfo(dent1.getChildAt(0), dent1.getChildAt(1), dent1.getChildAt(2) ))
        }
    }


}