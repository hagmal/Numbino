package com.example.numbino_s305896.ui

// Klasse for å samle info om tilstanden til spill-skjermen
data class SpillUiState (
    val regnestykke: String = "",
    val brukerSvar: String = "",
    val tilbakemelding: Int = 3,
    val ferdig: Boolean = false,
    val erSvarFeil: Boolean = false,
    val current: Int = 0,
    val total: Int = 0
)