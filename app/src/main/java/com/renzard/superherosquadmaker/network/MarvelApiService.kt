package com.renzard.superherosquadmaker.network


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.renzard.superherosquadmaker.network.response.Result
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


//https://gateway.marvel.com:443/v1/public/characters?apikey=f744427ffa2a0cd07771f0d93ade47e9
//interface for all the get requests
interface MarvelApiService {
    @GET("characters")
    fun getAllCharacters(

    ): Deferred<Result>

    //intercepts all the get requests with the api key timestamp and hash
    companion object {
        operator fun invoke(
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
            val logging = HttpLoggingInterceptor()
            logging.setLevel(BASIC)

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(logging)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://gateway.marvel.com:443/v1/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(MarvelApiService::class.java)


        }
    }
}