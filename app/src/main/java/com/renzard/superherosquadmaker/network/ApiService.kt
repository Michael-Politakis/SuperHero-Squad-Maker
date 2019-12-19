package com.renzard.superherosquadmaker.network

import com.renzard.superherosquadmaker.network.response.Result
import retrofit2.http.GET


//https://gateway.marvel.com:443/v1/public/characters?orderBy=name&apikey=f744427ffa2a0cd07771f0d93ade47e9

interface ApiInterface {

    @GET("/v1/public/characters")
    fun getAllCharacters(): Result
}