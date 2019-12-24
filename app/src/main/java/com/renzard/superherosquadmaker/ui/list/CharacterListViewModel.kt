package com.renzard.superherosquadmaker.ui.list

import androidx.lifecycle.ViewModel
import com.renzard.superherosquadmaker.data.repository.CharacterRepository
import com.renzard.superherosquadmaker.internal.lazyDeferred

class CharacterListViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    val characters by lazyDeferred {
        characterRepository.getCharacterList()
    }
}
