package com.robertapikyan.bleConnection.dataHandlers.pherifieral.desc

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattDescriptor
import android.bluetooth.BluetoothGattServer
import com.robertapikyan.bleConnection.codecs.BleCodec
import com.robertapikyan.bleConnection.packageCollector.BleBufferedPacketCollector
import com.robertapikyan.bleConnection.packageCollector.BlePacketCollector

open class BlePerDescBufferedDataHandler<D>(
    descriptor: BluetoothGattDescriptor,
    gattServer: BluetoothGattServer,
    private val codec: BleCodec<D>,
    private val collector: BleBufferedPacketCollector
) :
    BlePerDescDataHandler(descriptor, gattServer) {

    final override fun onWriteRequest(
        device: BluetoothDevice?,
        requestId: Int,
        descriptor: BluetoothGattDescriptor?,
        preparedWrite: Boolean,
        responseNeeded: Boolean,
        offset: Int,
        value: ByteArray?
    ) {
        super.onWriteRequest(
            device,
            requestId,
            descriptor,
            preparedWrite,
            responseNeeded,
            offset,
            value
        )
        gattServer.sendResponse(device, requestId, BluetoothGatt.GATT_SUCCESS, 0, null)

        if (value != null) with(collector) {
            getCollectCompleteNotifier().clearAndAdd(
                object : BlePacketCollector.OnPacketCollectCompleteCallback {
                    override fun onCollectComplete(bytes: ByteArray) = onWriteDataRequest(
                        device,
                        requestId,
                        descriptor,
                        preparedWrite,
                        responseNeeded,
                        offset,
                        codec.fromByteArray(bytes)
                    )
                }
            )
        }
    }

    protected open fun onWriteDataRequest(
        device: BluetoothDevice?,
        requestId: Int,
        descriptor: BluetoothGattDescriptor?,
        preparedWrite: Boolean,
        responseNeeded: Boolean,
        offset: Int,
        data: D
    ) {

    }
}