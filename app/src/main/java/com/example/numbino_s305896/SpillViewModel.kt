package com.example.numbino_s305896

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numbino_s305896.ui.SpillUiState
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
    private lateinit var currentRegnestykke: String
    private var brukteRegnestykker: MutableSet<String> = mutableSetOf()


    private fun velgTilfeldigRegnestykke(): String? {
        if (alleRegnestykker.isEmpty()) return null
        currentRegnestykke = alleRegnestykker.random()
        if (brukteRegnestykker.contains(currentRegnestykke)) {
            return velgTilfeldigRegnestykke()
        } else {
            brukteRegnestykker.add(currentRegnestykke)
            return currentRegnestykke
        }
    }
    fun startNyttSpill() {
        brukteRegnestykker.clear()

        val valgt = velgTilfeldigRegnestykke()
        _uiState.value = if (valgt == null) {
            SpillUiState(
                regnestykke = "",
                brukerSvar = "",
                tilbakemelding = 3,
                ferdig = true,
                erSvarFeil = false
            )
        } else {
            currentRegnestykke = valgt
            SpillUiState(
                regnestykke = valgt,
                brukerSvar = "",
                tilbakemelding = 3,
                ferdig = false,
                erSvarFeil = false
            )
        }
    }

    fun nesteOppgave() {
        val valgt = velgTilfeldigRegnestykke()
        _uiState.value = if (valgt == null) {
            _uiState.value.copy(ferdig = true)
        } else {
            currentRegnestykke = valgt
            _uiState.value.copy(
                regnestykke = valgt,
                brukerSvar = "",
                tilbakemelding = 3,
                erSvarFeil = false
            )
        }
    }

    fun sjekkSvar(tall: Int) {
        // Finner indeks for gjeldende regnestykke
        val id = alleRegnestykker.indexOf(currentRegnestykke)
        if (id == -1) {
            return
        }
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
}