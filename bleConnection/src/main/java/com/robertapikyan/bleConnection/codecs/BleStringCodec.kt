package com.robertapikyan.bleConnection.codecs

class BleStringCodec : BleCodec<String> {

    override fun toByteArray(data: String): ByteArray {
        return data.toByteArray()
    }

    override fun fromByteArray(byteArray: ByteArray): String {
        return String(byteArray)
    }
}