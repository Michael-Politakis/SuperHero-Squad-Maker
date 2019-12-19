package com.renzard.superherosquadmaker.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(characterEntry: Result)

    @Query("select * from character where id")
    fun getCharacterInfo(): LiveData<Result>
}