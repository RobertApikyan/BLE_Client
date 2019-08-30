package com.robertapikyan.bleConnection.dataHandlers.pherifieral.desc

import android.bluetooth.BluetoothGattDescriptor
import android.bluetooth.BluetoothGattServer
import com.robertapikyan.bleConnection.codecs.BleStringCodec
import com.robertapikyan.bleConnection.packageCollector.BleBufferedPacketCollector

open class BlePerDescStringDataHandler(
    descriptor: BluetoothGattDescriptor,
    gattServer: BluetoothGattServer
) : BlePerDescBufferedDataHandler<String>(
    descriptor, gattServer,
    codec = BleStringCodec(),
    collector = BleBufferedPacketCollector()
)