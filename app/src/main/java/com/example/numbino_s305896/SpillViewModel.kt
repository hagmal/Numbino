package com.example.numbino_s305896

import androidx.lifecycle.ViewModel
import com.example.numbino_s305896.ui.SpillUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class SpillViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SpillUiState())
    val uiState: StateFlow<SpillUiState> = _uiState.asStateFlow()

    private var alleRegnestykker: List<String> = emptyList()
    private lateinit var currentRegnestykke: String
    private var brukteRegnestykker: MutableSet<String> = mutableSetOf()


    private fun velgTilfeldigRegnestykke(): String {
        currentRegnestykke = alleRegnestykker.random()
        if (brukteRegnestykker.contains(currentRegnestykke)) {
            return velgTilfeldigRegnestykke()
        } else {
            brukteRegnestykker.add(currentRegnestykke)
            return currentRegnestykke
        }
    }

    fun nullstillSpill() {
        brukteRegnestykker.clear()
        if (alleRegnestykker.isEmpty()) {
            _uiState.value = SpillUiState()
            return
        }
        val valgt = velgTilfeldigRegnestykke()
        _uiState.value = SpillUiState(
            regnestykke = valgt,
            brukerSvar = "",
            tilbakemelding = 3,
            ferdig = false
        )
    }
}