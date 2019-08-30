package com.robertapikyan.bleConnection.codecs

class BleByteArrayCodec : BleCodec<ByteArray> {
    override fun toByteArray(data: ByteArray) = data

    override fun fromByteArray(byteArray: ByteArray) = byteArray
}