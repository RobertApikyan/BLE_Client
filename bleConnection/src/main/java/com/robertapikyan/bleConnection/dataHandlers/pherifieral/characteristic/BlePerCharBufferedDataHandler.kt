package com.robertapikyan.bleConnection.dataHandlers.pherifieral.characteristic

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCharacteristic
import com.robertapikyan.bleConnection.BleStatusCodes.NONE
import com.robertapikyan.bleConnection.codecs.BleCodec
import com.robertapikyan.bleConnection.packageCollector.BlePacketCollector
import com.robertapikyan.bleConnection.packageEmitter.BlePacketEmitter

open class BlePerCharBufferedDataHandler<D>(
    private val codec: BleCodec<D>,
    private val emitter: BlePacketEmitter,
    private val collector: BlePacketCollector
) : BlePerCharDataHandler() {

    override fun onWriteRequest(
        device: BluetoothDevice?,
        requestId: Int,
        characteristic: BluetoothGattCharacteristic?,
        preparedWrite: Boolean,
        responseNeeded: Boolean,
        offset: Int,
        value: ByteArray?
    ) {
        super.onWriteRequest(device, requestId, characteristic, preparedWrite, responseNeeded, offset, value)
        if (value != null) with(collector) {
            getCollectCompleteNotifier().clearAndAdd(
                object : BlePacketCollector.OnPacketCollectCompleteCallback {
                    override fun onCollectComplete(bytes: ByteArray) = onWriteDataRequest(
                        device,
                        requestId,
                        characteristic,
                        preparedWrite,
                        responseNeeded,
                        offset,
                        codec.fromByteArray(bytes)
                    )
                }
            )
            getCollectNotifier().clearAndAdd(object : BlePacketCollector.OnPacketCollectCallback {
                override fun onCollect(bytes: ByteArray) {
                    sendResponse(device, requestId, BluetoothGatt.GATT_FAILURE, offset, NONE)
                }
            })
            collect(value)
        }
    }

    protected open fun onWriteDataRequest(
        device: BluetoothDevice?,
        requestId: Int,
        characteristic: BluetoothGattCharacteristic?,
        preparedWrite: Boolean,
        responseNeeded: Boolean,
        offset: Int,
        data: D
    ) {

    }

    fun notifyData(device: BluetoothDevice, data: D) {
        val byteArray = codec.toByteArray(data)
        with(emitter) {
            getEmitNotifier().clearAndAdd(object : BlePacketEmitter.OnEmitCallback {
                override fun onEmit(bytes: ByteArray) {
                    super@BlePerCharBufferedDataHandler.notifyData(device, bytes)
                }
            })
            emit(byteArray)
        }
    }

    fun sendResponse(
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