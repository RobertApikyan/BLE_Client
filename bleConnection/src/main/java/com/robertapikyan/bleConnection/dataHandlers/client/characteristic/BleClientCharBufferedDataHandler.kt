package com.robertapikyan.bleConnection.dataHandlers.client.characteristic

import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCharacteristic
import com.robertapikyan.bleConnection.codecs.BleCodec
import com.robertapikyan.bleConnection.packageCollector.BleBufferedPacketCollector
import com.robertapikyan.bleConnection.packageCollector.BlePacketCollector
import com.robertapikyan.bleConnection.packageEmitter.BleBufferedPacketEmitter
import com.robertapikyan.bleConnection.packageEmitter.BlePacketEmitter

open class BleClientCharBufferedDataHandler<D>(
    private val codec: BleCodec<D>,
    private val collector: BleBufferedPacketCollector,
    private val emitter: BleBufferedPacketEmitter
) : BleClientCharDataHandler() {

    override fun onChanged(gatt: BluetoothGatt?, characteristic: BluetoothGattCharacteristic?) {
        super.onChanged(gatt, characteristic)
        collector.getCollectCompleteNotifier().clearAndAdd(object : BlePacketCollector.OnPacketCollectCompleteCallback {
            override fun onCollectComplete(bytes: ByteArray) {
                val data = codec.fromByteArray(bytes)
                onDataChanged(data)
            }
        })
        if (characteristic?.value != null)
            collector.collect(characteristic.value)
    }

    override fun onRead(gatt: BluetoothGatt?, characteristic: BluetoothGattCharacteristic?, status: Int) {
        super.onRead(gatt, characteristic, status)
        val data = codec.fromByteArray(characteristic?.value ?: "".toByteArray())
        onReadData(gatt, characteristic, status, data)
    }

    override fun onWrite(
        gatt: BluetoothGatt?,
        characteristic: BluetoothGattCharacteristic?,
        status: Int
    ) {
        super.onWrite(gatt, characteristic, status)
        val data = codec.fromByteArray(characteristic?.value ?: "".toByteArray())
        onWriteData(gatt, characteristic, status, data)
    }

    open fun onReadData(
        gatt: BluetoothGatt?,
        characteristic: BluetoothGattCharacteristic?,
        status: Int,
        data: D
    ) {

    }

    open fun onWriteData(
        gatt: BluetoothGatt?,
        characteristic: BluetoothGattCharacteristic?,
        status: Int,
        data: D
    ) {

    }

    open fun onDataChanged(data: D) {

    }

    fun writeData(data: D) {
        val byteArray = codec.toByteArray(data)
        emitter.getEmitNotifier().clearAndAdd(object : BlePacketEmitter.OnEmitCallback {
            override fun onEmit(bytes: ByteArray) {
                write(bytes)
            }
        })
        emitter.emit(byteArray)
    }
}