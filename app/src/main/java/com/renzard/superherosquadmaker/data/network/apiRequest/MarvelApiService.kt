package com.renzard.superherosquadmaker.data.network.apiRequest


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.renzard.superherosquadmaker.data.network.ConnectivityInterceptor
import com.renzard.superherosquadmaker.data.network.response.CharacterResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//https://gateway.marvel.com:443/v1/public/characters?apikey=f744427ffa2a0cd07771f0d93ade47e9
//interface for all the get requests
interface MarvelApiService {

    @GET("characters")
    fun getAllCharactersAsync(
        @Query("offset") characterOffset: Int,
        @Query("limit") characterLimit: Int
    ): Deferred<CharacterResponse>

    //intercepts all the get requests with the api key timestamp and hash
    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): MarvelApiService {

            val timestamp: Long = System.currentTimeMillis()
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url
                    .newBuilder()

                    .addQueryParameter("ts", timestamp.toString())
                    .addQueryParameter("apikey", ApiRequestEncryption.publicKey)
                    .addQueryParameter("hash", ApiRequestEncryption.toMD5hash(timestamp.toString()))
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
            //checking the url
            val logging = HttpLoggingInterceptor()
            logging.apply { logging.level = HttpLoggingInterceptor.Level.BASIC }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .addInterceptor(logging)
                .build()



            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://gateway.marvel.com:443/v1/public/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MarvelApiService::class.java)

        }
    }
}