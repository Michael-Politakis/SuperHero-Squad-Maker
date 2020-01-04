package com.renzard.superherosquadmaker.data.repository

import androidx.lifecycle.LiveData
import com.renzard.superherosquadmaker.data.db.details.CharacterDetailEntry
import com.renzard.superherosquadmaker.data.db.list.CharacterListSimpleEntry
import com.renzard.superherosquadmaker.data.db.selected.SelectedCharacters

interface CharacterRepository {
    suspend fun getCharacterList(): LiveData<out List<CharacterListSimpleEntry>>

    suspend fun getCharacterDetails(id: Int): LiveData<out CharacterDetailEntry>

    suspend fun getSelected(): LiveData<out List<SelectedCharacters>>

    suspend fun getSelectedID(id: Int): LiveData<out SelectedCharacters>

    suspend fun setSelected(selectedHeroId: Int)

    suspend fun setNotSelected(selectedHeroId: Int)

//    suspend fun getComics(id: Int) : LiveData<out List<ComicEntry>>

}