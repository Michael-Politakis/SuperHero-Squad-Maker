package com.renzard.superherosquadmaker.data.network.apiRequest

import java.security.MessageDigest


//this object makes all the necessary changes to the keys and the timestamp for for the api request
object ApiRequestEncryption {
    //getters for the keys
    private val _privateKey = "1f6a9f3b74626769fe48f479ca203987fd7c9cec"
    val privateKey =
        _privateKey
    private val _publicKey = "406c7ecb65228cf129bba9073093bef5"
    val publicKey =
        _publicKey
    //hash creator
    fun toMD5hash(timeString: String): String {
        val hash = MessageDigest.getInstance("MD5").digest(
            timeString.toByteArray()
                    + privateKey.toByteArray() + publicKey.toByteArray()
        )
        return hash.toHex()
    }

    //format the the hash
    fun ByteArray.toHex(): String {
        return joinToString("") { "%02x".format(it) }
    }



}