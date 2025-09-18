package com.example.numbino_s305896

import androidx.lifecycle.ViewModel

data class SpillUiTilstand (
    val nåværendeSpørsmål: String = "",
    val nåværendeSvar: Int = 0,
    val tilbakemelding: Int = 3,
    val trykketNummer: Int? = null,
    val nåværendeSpørsmålIndeks: Int = 0
)

class SpillViewModel : ViewModel() {
}