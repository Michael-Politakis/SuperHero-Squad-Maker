package com.renzard.superherosquadmaker.data.network.apiRequest


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.renzard.superherosquadmaker.data.network.ConnectivityInterceptor
import com.renzard.superherosquadmaker.data.network.response.CharacterResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


//https://gateway.marvel.com:443/v1/public/characters?apikey=f744427ffa2a0cd07771f0d93ade47e9
//interface for all the get requests
interface MarvelApiService {

    @GET("characters")
    fun getAllCharacters(characterOffset: Int, characterLimit: Int): Deferred<CharacterResponse>

    //intercepts all the get requests with the api key timestamp and hash
    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): MarvelApiService {

            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("ts", ApiRequestEncryption.getTimeStamp())
                    .addQueryParameter("apikey", ApiRequestEncryption.publicKey)
                    .addQueryParameter("hash", ApiRequestEncryption.toMD5hash())
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
            //checking the url
//            val logging = HttpLoggingInterceptor()
//            logging.setLevel(BASIC)

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
//                .addInterceptor(logging)
                .addInterceptor(connectivityInterceptor)
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