package com.robertapikyan.bleConnection.dataHandlers.client.descriptor

import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattDescriptor
import com.robertapikyan.bleConnection.BleStatusCodes
import com.robertapikyan.bleConnection.codecs.BleCodec
import com.robertapikyan.bleConnection.packageEmitter.BleBufferedPacketEmitter
import com.robertapikyan.bleConnection.packageEmitter.BlePacketEmitter

open class BleClientDescBufferedDataHandler<D>(
    private val codec: BleCodec<D>,
    private val emitter: BleBufferedPacketEmitter
) : BleClientDescDataHandler() {

    fun writeData(data: D) {
        val byteArray = codec.toByteArray(data)
        emitter.getEmitNotifier().clearAndAdd(object : BlePacketEmitter.OnEmitCallback {
            override fun onEmit(bytes: ByteArray) {
                super@BleClientDescBufferedDataHandler.write(bytes)
            }
        })
        emitter.emit(byteArray)
    }

    override fun onRead(gatt: BluetoothGatt?, descriptor: BluetoothGattDescriptor?, status: Int) {
        super.onRead(gatt, descriptor, status)
        val data = codec.fromByteArray(descriptor?.value ?: "".toByteArray())
        if (data!= BleStatusCodes.NONE)
        onReadData(gatt, descriptor, status, data)
    }

    override fun onWrite(gatt: BluetoothGatt?, descriptor: BluetoothGattDescriptor?, status: Int) {
        super.onWrite(gatt, descriptor, status)
        val data = codec.fromByteArray(descriptor?.value ?: "".toByteArray())
        onWriteData(gatt, descriptor, status, data)
    }

    open fun onReadData(
        gatt: BluetoothGatt?,
        descriptor: BluetoothGattDescriptor?,
        status: Int,
        data: D
    ) {

    }

    open fun onWriteData(
        gatt: BluetoothGatt?,
        descriptor: BluetoothGattDescriptor?,
        status: Int,
        data: D
    ) {

    }
}