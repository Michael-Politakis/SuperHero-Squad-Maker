package com.renzard.superherosquadmaker.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.renzard.superherosquadmaker.data.repository.CharacterRepository
import com.renzard.superherosquadmaker.internal.lazyDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val characterId: Int,
    private val characterRepository: CharacterRepository
) : ViewModel() {
    val character by lazyDeferred {
        characterRepository.getCharacterDetails(characterId)
    }

    val isSelectedCharacter by lazyDeferred {
        characterRepository.getSelectedID(characterId)
    }


    fun selectCharacter(isSelected: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            if (isSelected) {
                characterRepository.setSelected(characterId)
            } else {
                characterRepository.setNotSelected(characterId)
            }
        }
    }
}
