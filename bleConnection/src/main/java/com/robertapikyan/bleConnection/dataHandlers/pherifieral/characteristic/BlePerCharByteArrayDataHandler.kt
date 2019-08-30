package com.robertapikyan.bleConnection.dataHandlers.pherifieral.characteristic

import com.robertapikyan.bleConnection.codecs.BleByteArrayCodec
import com.robertapikyan.bleConnection.packageCollector.BleBufferedPacketCollector
import com.robertapikyan.bleConnection.packageEmitter.BleBufferedPacketEmitter

open class BlePerCharByteArrayDataHandler : BlePerCharBufferedDataHandler<ByteArray>(
    codec = BleByteArrayCodec(),
    emitter = BleBufferedPacketEmitter(),
    collector = BleBufferedPacketCollector()
)