package com.renzard.superherosquadmaker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.renzard.superherosquadmaker.data.network.CharacterNetworkDataSourceImpl
import com.renzard.superherosquadmaker.data.network.ConnectivityInterceptorImpl
import com.renzard.superherosquadmaker.data.network.apiRequest.MarvelApiService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        recyclerView = findViewById<RecyclerView>(R.id.listOfCharacters).apply{
//
//            setHasFixedSize(true)
//            layoutManager = viewManager
//            adapter = viewAdapter
//
//        }

        val marvelApiService =
            MarvelApiService(ConnectivityInterceptorImpl(this.applicationContext))
        val characterNetworkDataSource = CharacterNetworkDataSourceImpl(marvelApiService)

        characterNetworkDataSource.downloadedCharacterData.observe(this, Observer {
            charResponse.text = it.toString()
        }

        )
        GlobalScope.launch(Dispatchers.Main) {
            characterNetworkDataSource.fetchCharacterData()
        }
    }

}
