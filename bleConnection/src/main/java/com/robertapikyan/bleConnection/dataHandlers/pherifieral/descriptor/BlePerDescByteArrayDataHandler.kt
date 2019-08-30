package com.robertapikyan.bleConnection.dataHandlers.pherifieral.descriptor

import com.robertapikyan.bleConnection.codecs.BleByteArrayCodec
import com.robertapikyan.bleConnection.packageCollector.BleBufferedPacketCollector

open class BlePerDescByteArrayDataHandler :
    BlePerDescBufferedDataHandler<ByteArray>(
        codec = BleByteArrayCodec(),
        collector = BleBufferedPacketCollector()
    )