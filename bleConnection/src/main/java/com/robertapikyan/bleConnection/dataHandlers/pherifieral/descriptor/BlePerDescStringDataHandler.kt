package com.robertapikyan.bleConnection.dataHandlers.pherifieral.descriptor

import com.robertapikyan.bleConnection.codecs.BleStringCodec
import com.robertapikyan.bleConnection.packageCollector.BleBufferedPacketCollector

open class BlePerDescStringDataHandler : BlePerDescBufferedDataHandler<String>(
    codec = BleStringCodec(),
    collector = BleBufferedPacketCollector()
)