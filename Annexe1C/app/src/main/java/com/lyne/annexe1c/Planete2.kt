package com.lyne.annexe1c

data class Planete2(private var nom: String, private var nbSatellites: Int) {
    var nbHabitants: Long = 0

    init {
        if (nom == "Terre") {
            nbHabitants = 8000000000L // 8 milliards pour la Terre
        } else {
            nbHabitants = 0          // 0 pour les autres par défaut
        }
    }
}
