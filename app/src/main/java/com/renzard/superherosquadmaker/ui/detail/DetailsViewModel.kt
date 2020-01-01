package com.renzard.superherosquadmaker.ui.detail

import androidx.lifecycle.ViewModel
import com.renzard.superherosquadmaker.data.repository.CharacterRepository
import com.renzard.superherosquadmaker.internal.lazyDeferred

class DetailsViewModel(
    private val characterId: Int,
    private val characterRepository: CharacterRepository
) : ViewModel() {
    val character by lazyDeferred {
        characterRepository.getCharacterDetails(characterId)
    }
}
