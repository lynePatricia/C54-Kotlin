package com.lyne.tp1

import android.content.Context
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon

object Modele : Sujet{
    private val observateurs = ArrayList<ObservateurMusique>()

    fun lireJSON(context : Context, lien: String) {

        val queue = Volley.newRequestQueue(context)
        var url = lien

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { reponse ->
                val li: ListeMusique = Klaxon().parse<ListeMusique>(reponse) ?: ListeMusique()
                avertirObservateurs(li)
            },
            { makeText(context, "Ne fonctionne pas", LENGTH_LONG).show() })

//        val stringRequest = StringRequest(
//            Request.Method.GET,
//            url,
//            { reponse ->
//                try {
//                    val li: ListeMusique = Klaxon().parse<ListeMusique>(reponse) ?: ListeMusique()
//                    avertirObservateurs(li)
//                    makeText(context, "allo", LENGTH_LONG).show()
//                } catch (e: Exception) {
//                    // Affiche l'erreur de parsing Klaxon dans les logs
//                    e.printStackTrace()
//                    makeText(context, "Erreur Parse: ${e.message}", LENGTH_LONG).show()
//                }
//            },
//            { error ->
//                // Affiche l'erreur réseau exacte
//                error.printStackTrace()
//                val messageErreur = error.networkResponse?.statusCode?.toString() ?: error.message ?: "Erreur inconnue"
//                makeText(context, "Erreur réseau: $messageErreur", LENGTH_LONG).show()
//            }
//        )

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