package com.robertapikyan.bleConnection.dataHandlers.pherifieral.descriptor

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattDescriptor
import com.robertapikyan.bleConnection.BleStatusCodes
import com.robertapikyan.bleConnection.BleStatusCodes.NONE
import com.robertapikyan.bleConnection.codecs.BleCodec
import com.robertapikyan.bleConnection.packageCollector.BleBufferedPacketCollector
import com.robertapikyan.bleConnection.packageCollector.BlePacketCollector

open class BlePerDescBufferedDataHandler<D>(
    private val codec: BleCodec<D>,
    private val collector: BleBufferedPacketCollector
) :
    BlePerDescDataHandler() {

    override fun onWriteRequest(
        device: BluetoothDevice?,
        requestId: Int,
        descriptor: BluetoothGattDescriptor?,
        preparedWrite: Boolean,
        responseNeeded: Boolean,
        offset: Int,
        value: ByteArray?
    ) {
        super.onWriteRequest(device, requestId, descriptor, preparedWrite, responseNeeded, offset, value)
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
            getCollectNotifier().clearAndAdd(object : BlePacketCollector.OnPacketCollectCallback {
                override fun onCollect(bytes: ByteArray) {
                    sendResponse(device, requestId, BluetoothGatt.GATT_FAILURE, 0, NONE)
                }
            })
            collect(value)
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

    protected fun sendResponse(
        device: BluetoothDevice?,
        requestId: Int,
        status: Int,
        offset: Int,
        data: D
    ) {
        val byteArray = codec.toByteArray(data)
        super.sendResponse(device, requestId, status, offset, byteArray)
    }
}