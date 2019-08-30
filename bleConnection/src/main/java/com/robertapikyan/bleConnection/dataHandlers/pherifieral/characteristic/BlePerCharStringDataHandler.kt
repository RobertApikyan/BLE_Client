package com.robertapikyan.bleConnection.dataHandlers.pherifieral.characteristic

import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattServer
import com.robertapikyan.bleConnection.codecs.BleStringCodec
import com.robertapikyan.bleConnection.packageCollector.BleBufferedPacketCollector
import com.robertapikyan.bleConnection.packageEmitter.BleBufferedPacketEmitter

open class BlePerCharStringDataHandler : BlePerCharBufferedDataHandler<String>(
    codec = BleStringCodec(),
    emitter = BleBufferedPacketEmitter(),
    collector = BleBufferedPacketCollector()
)