package com.renzard.superherosquadmaker.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.renzard.superherosquadmaker.data.repository.CharacterRepository

class DetailsViewModelFactory(
    private val characterId: Int,
    private val characterRepository: CharacterRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(characterId, characterRepository) as T
    }
}