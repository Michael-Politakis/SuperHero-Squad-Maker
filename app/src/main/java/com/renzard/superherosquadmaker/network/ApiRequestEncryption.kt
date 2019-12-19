package com.renzard.superherosquadmaker.network

import java.security.MessageDigest
import java.text.SimpleDateFormat

object ApiRequestEncryption {
    private val _privateKey = "1f6a9f3b74626769fe48f479ca203987fd7c9cec"
    val privateKey = _privateKey
    private val _publicKey = "406c7ecb65228cf129bba9073093bef5"
    val publicKey = _publicKey
    fun toMD5hash(): String {
        val hash = MessageDigest.getInstance("MD5").digest(
            getTimeStamp().toByteArray()
                    + privateKey.toByteArray() + publicKey.toByteArray()
        )
        return hash.toHex()
    }

    fun ByteArray.toHex(): String {
        return joinToString("") { "%02x".format(it) }
    }

    fun getTimeStamp(): String {
        val timestamp: Long = System.currentTimeMillis()
        return SimpleDateFormat("yyyyMMddHHmmSS").format(timestamp)
    }


}