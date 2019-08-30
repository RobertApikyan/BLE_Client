package com.robertapikyan.bleConnection.codecs

interface BleCodec<D>{
    fun toByteArray(data:D):ByteArray
    fun fromByteArray(byteArray: ByteArray):D
}