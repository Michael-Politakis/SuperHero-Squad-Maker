package com.renzard.superherosquadmaker.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.renzard.superherosquadmaker.data.repository.CharacterRepository

class CharacterListViewModelFactory(
    private val characterRepository: CharacterRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharacterListViewModel(
            characterRepository
        ) as T
    }
}