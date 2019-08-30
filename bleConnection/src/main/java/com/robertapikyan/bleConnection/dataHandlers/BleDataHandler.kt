package com.robertapikyan.bleConnection.dataHandlers

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGattServer
import java.util.*

open class BleDataHandler(
    val uuid: UUID,
    private val gattServer: BluetoothGattServer
) {

    protected fun sendResponse(
        device: BluetoothDevice?,
        requestId: Int,
        status: Int,
        offset: Int,
        value: ByteArray?
    ) {
        gattServer.sendResponse(
            device, requestId, status, offset, value
        )
    }
}