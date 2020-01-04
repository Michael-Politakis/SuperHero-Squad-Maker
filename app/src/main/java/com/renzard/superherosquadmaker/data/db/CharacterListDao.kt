package com.renzard.superherosquadmaker.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.renzard.superherosquadmaker.data.db.details.CharacterDetailEntryImpl
import com.renzard.superherosquadmaker.data.db.entity.Result
import com.renzard.superherosquadmaker.data.db.list.CharacterListSimpleEntryImpl
import com.renzard.superherosquadmaker.data.db.selected.SelectedCharactersImpl

@Dao
interface CharacterListDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(characterListEntries: List<Result>)


    @Query("select * from character_data")
    fun getCharacterList(): LiveData<List<CharacterListSimpleEntryImpl>>

    @Query("select * from character_data where id = :id")
    fun getCharacterDetails(id: Int): LiveData<CharacterDetailEntryImpl>

//    @Query("select comics_name and comics_resourceURI from character_data where id = :id")
//    fun getComics(id: Int) : LiveData<ComicEntryImpl>

    @Query("select * from character_data where characterSelected = 1")
    fun getSelected(): LiveData<List<SelectedCharactersImpl>>

    @Query("select * from character_data where id = :id")
    fun getSelectedId(id: Int): LiveData<SelectedCharactersImpl>

    @Query("update character_data set characterSelected = 1 where id = :id")
    fun setSelected(id: Int)

    @Query("update character_data set characterSelected = 0 where id = :id")
    fun setNotSelected(id: Int)


}