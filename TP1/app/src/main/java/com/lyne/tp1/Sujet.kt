package com.lyne.tp1

interface Sujet {
    fun ajouterObservateur(o: ObservateurMusique)
    fun retirerObservateur(o: ObservateurMusique)
    fun avertirObservateurs(l: ListeMusique)
}