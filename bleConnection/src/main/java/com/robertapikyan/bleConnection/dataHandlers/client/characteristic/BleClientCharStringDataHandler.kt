package com.robertapikyan.bleConnection.dataHandlers.client.characteristic

import com.robertapikyan.bleConnection.codecs.BleStringCodec
import com.robertapikyan.bleConnection.packageCollector.BleBufferedPacketCollector
import com.robertapikyan.bleConnection.packageEmitter.BleBufferedPacketEmitter

open class BleClientCharStringDataHandler() : BleClientCharBufferedDataHandler<String>(
    codec = BleStringCodec(),
    collector = BleBufferedPacketCollector(),
    emitter = BleBufferedPacketEmitter()
)