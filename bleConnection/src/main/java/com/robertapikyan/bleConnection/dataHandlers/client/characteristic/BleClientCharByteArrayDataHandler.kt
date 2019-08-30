package com.robertapikyan.bleConnection.dataHandlers.client.characteristic

import com.robertapikyan.bleConnection.codecs.BleByteArrayCodec
import com.robertapikyan.bleConnection.packageCollector.BleBufferedPacketCollector
import com.robertapikyan.bleConnection.packageEmitter.BleBufferedPacketEmitter

open class BleClientCharByteArrayDataHandler : BleClientCharBufferedDataHandler<ByteArray>(
    codec = BleByteArrayCodec(),
    collector = BleBufferedPacketCollector(),
    emitter = BleBufferedPacketEmitter()
)