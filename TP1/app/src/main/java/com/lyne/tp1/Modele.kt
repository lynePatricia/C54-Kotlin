package com.lyne.tp1

import android.content.Context
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon

class Modele : Sujet{
    private val observateurs = ArrayList<ObservateurMusique>()
    //pour stocker la liste dans le modèle
    var listeMusiques: List<Musique> = emptyList()

    fun lireJSON(context : Context, lien: String){

        val queue = Volley.newRequestQueue(context)
        var url = lien

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { reponse ->
                val li: ListeMusique = Klaxon().parse<ListeMusique>(reponse) ?: ListeMusique()
                listeMusiques = li.musiques //stocke la liste dans le modèle
                avertirObservateurs(li)
            },
            { makeText(context, "Ne fonctionne pas", LENGTH_LONG).show() })

        queue.add(stringRequest)

    }

    override fun ajouterObservateur(o: ObservateurMusique) {
        observateurs.add(o)
    }

    override fun retirerObservateur(o: ObservateurMusique) {
        observateurs.remove(o)
    }

    override fun avertirObservateurs(l: ListeMusique) {
        for (o in observateurs)
            o.actualiser(l)
    }


}