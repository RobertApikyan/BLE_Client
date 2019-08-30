package com.robertapikyan.bleConnection.dataHandlers.client.descriptor

import com.robertapikyan.bleConnection.codecs.BleStringCodec
import com.robertapikyan.bleConnection.packageEmitter.BleBufferedPacketEmitter

open class BleClientDescStringDataHandler : BleClientDescBufferedDataHandler<String>(
    codec = BleStringCodec(),
    emitter = BleBufferedPacketEmitter()
)