package com.renzard.superherosquadmaker.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.renzard.superherosquadmaker.data.db.entity.characters.Result

@Database(
    entities = [Result::class, com.renzard.superherosquadmaker.data.db.entity.comics.Result::class],
    version = 1
)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterListDao

    companion object {
        @Volatile
        private var instance: CharacterDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CharacterDatabase::class.java, "character.db"
            )
                .build()
    }
}