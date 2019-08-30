package com.robertapikyan.bleConnection.dataHandlers.client.descriptor

import com.robertapikyan.bleConnection.codecs.BleByteArrayCodec
import com.robertapikyan.bleConnection.packageEmitter.BleBufferedPacketEmitter

open class BleClientDescByteArrayDataHandler : BleClientDescBufferedDataHandler<ByteArray>(
    codec = BleByteArrayCodec(),
    emitter = BleBufferedPacketEmitter()
)