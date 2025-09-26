package com.example.numbino_s305896.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SpillViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SpillUiState()) // Nåværende spilltilstand
    val uiState: StateFlow<SpillUiState> = _uiState.asStateFlow() // Henter inn og gir UI info om tilstanden

    var alleRegnestykker: List<String> = emptyList() // Alle regnestykkene
    var alleSvar: List<Int> = emptyList() //Alle fasitsvar

    private var indekser: List<Int> = emptyList() // Liste med indekser for å styre rekkefølgen i et spill
    private var currentIndex: Int = 0 // Nåværende regnestykke spilleren er på

    // Starter nytt spill med valgt antall oppgaver
    fun startNyttSpill(antall: Int) {
        // Hvis dataene ikke lastes inn riktig, får man ingen oppgaver (spill markeres som ferdig)
        if (alleRegnestykker.isEmpty() || alleRegnestykker.size != alleSvar.size){
            _uiState.value = SpillUiState(ferdig = true)
            return
        }

        // Velger tilfeldige oppgaver, og tar med så mange som spilleren har valgt i preferanser
        indekser = alleRegnestykker.indices
            .shuffled()
            .take(minOf(antall, alleRegnestykker.size))

        // Starter på første oppgave
        currentIndex = 0
        visGjeldendeOppgave()
    }

    // Går videre til neste oppgave.
    fun nesteOppgave() {
        currentIndex++
        if (currentIndex >= indekser.size) {
            fullforSpill() // Hvis man er ferdig med alle oppgaver, avslutter spillet
        } else {
            visGjeldendeOppgave()
        }
    }

    // Sjekker om svaret fra brukeren er riktig
    fun sjekkSvar(tall: Int) {
        val reg = _uiState.value.regnestykke
        val id = alleRegnestykker.indexOf(reg)
        if (id == -1) return

        // Finn fasitsvaret, sjekker om brukerens svar matcher
        val riktig = alleSvar.getOrNull(id)
        val erRiktig = (riktig != null && tall == riktig)

        // Oppdaterer UI-tilstanden med tilbakemelding og svar
        _uiState.value = _uiState.value.copy(
            brukerSvar = tall.toString(),
            tilbakemelding = if (erRiktig) 1 else 2, // 1=riktig, 2=feil
            erSvarFeil = !erRiktig
        )

        // Hopper automatisk til neste oppgave hvis riktig svar
        if (erRiktig) {
            viewModelScope.launch {
                delay(1500) // Venter litt før den hopper videre
                nesteOppgave()
            }
        }
    }

    /* Hjelpemetoder */
    // Viser gjeldende regnestykke
    private fun visGjeldendeOppgave() {
        val id = indekser[currentIndex]
        _uiState.value = SpillUiState(
            regnestykke = alleRegnestykker[id],
            brukerSvar = "",
            tilbakemelding = 3,
            ferdig = false,
            erSvarFeil = false,
            current = currentIndex + 1,
            total = indekser.size
        )
    }

    // Avslutter spill når alle regnestykker er tatt
    private fun fullforSpill() {
        _uiState.value = _uiState.value.copy(
            regnestykke = "",
            brukerSvar = "",
            tilbakemelding = 3,
            ferdig = true,
            erSvarFeil = false,
            current = indekser.size,
            total = indekser.size
        )
    }
}