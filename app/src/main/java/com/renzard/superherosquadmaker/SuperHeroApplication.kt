package com.renzard.superherosquadmaker

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.renzard.superherosquadmaker.data.db.CharacterDatabase
import com.renzard.superherosquadmaker.data.network.CharacterNetworkDataSource
import com.renzard.superherosquadmaker.data.network.CharacterNetworkDataSourceImpl
import com.renzard.superherosquadmaker.data.network.ConnectivityInterceptor
import com.renzard.superherosquadmaker.data.network.ConnectivityInterceptorImpl
import com.renzard.superherosquadmaker.data.network.apiRequest.MarvelApiService
import com.renzard.superherosquadmaker.data.repository.CharacterRepository
import com.renzard.superherosquadmaker.data.repository.CharacterRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton


class SuperHeroApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@SuperHeroApplication))

        //instance() getting the context
        bind() from singleton { CharacterDatabase(instance()) }
        bind() from singleton { instance<CharacterDatabase>().characterDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { MarvelApiService(instance()) }
        bind<CharacterNetworkDataSource>() with singleton { CharacterNetworkDataSourceImpl(instance()) }
        bind<CharacterRepository>() with singleton {
            CharacterRepositoryImpl(
                instance(),
                instance()
            )
        }
    }

    //initializes time for isFetchDataNeeded
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}