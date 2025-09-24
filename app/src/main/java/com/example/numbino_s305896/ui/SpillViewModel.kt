package com.example.numbino_s305896.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SpillViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SpillUiState())
    val uiState: StateFlow<SpillUiState> = _uiState.asStateFlow()

    var alleRegnestykker: List<String> = emptyList()
    var alleSvar: List<Int> = emptyList()

    // Holder orden på rekkefølgen på regnestykkene i nåværende økt
    private var indekser: List<Int> = emptyList()
    private var currentIndex: Int = 0

    // Starter nytt spill med valgt antall oppgaver
    fun startNyttSpill(antall: Int) {
        if (alleRegnestykker.isEmpty() || alleRegnestykker.size != alleSvar.size){
            _uiState.value = SpillUiState(ferdig = true)
            return
        }

        indekser = alleRegnestykker.indices
            .shuffled()
            .take(minOf(antall, alleRegnestykker.size))

        currentIndex = 0
        visGjeldendeOppgave()
    }

    fun nesteOppgave() {
        currentIndex++
        if (currentIndex >= indekser.size) {
            fullforSpill()
        } else {
            visGjeldendeOppgave()
        }
    }

    fun sjekkSvar(tall: Int) {
        val reg = _uiState.value.regnestykke
        val id = alleRegnestykker.indexOf(reg)
        if (id == -1) return

        // Finn fasitsvaret
        val riktig = alleSvar.getOrNull(id)
        val erRiktig = (riktig != null && tall == riktig)

        // Oppdaterer UI-tilstanden
        _uiState.value = _uiState.value.copy(
            brukerSvar = tall.toString(),
            tilbakemelding = if (erRiktig) 1 else 2,
            erSvarFeil = !erRiktig
        )
        // Hopper automatisk til neste oppgave hvis riktig svar
        if (erRiktig) {
            viewModelScope.launch {
                delay(1500)
                nesteOppgave()
            }
        }
    }

    // Hjelpemetoder

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