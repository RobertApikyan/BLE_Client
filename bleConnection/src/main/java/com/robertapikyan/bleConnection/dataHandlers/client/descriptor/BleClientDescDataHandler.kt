package com.robertapikyan.bleConnection.dataHandlers.client.descriptor

import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattDescriptor
import com.robertapikyan.bleConnection.gattCallbacks.BluetoothGattCallbacks

open class BleClientDescDataHandler : BluetoothGattCallbacks.OnDescriptorReadCallback,
    BluetoothGattCallbacks.OnDescriptorWriteCallback {

    protected lateinit var desc: BluetoothGattDescriptor
    protected lateinit var bluetoothGatt: BluetoothGatt

    fun setupWithDescriptor(descriptor: BluetoothGattDescriptor) {
        this.desc = descriptor
    }

    fun setupWithGatt(bluetoothGatt: BluetoothGatt) {
        this.bluetoothGatt = bluetoothGatt
    }


    final override fun onDescriptorRead(gatt: BluetoothGatt?, descriptor: BluetoothGattDescriptor?, status: Int) {
        if (this.desc.uuid == descriptor?.uuid) {
            onRead(gatt, descriptor, status)
        }
    }

    final override fun onDescriptorWrite(gatt: BluetoothGatt?, descriptor: BluetoothGattDescriptor?, status: Int) {
        if (this.desc.uuid == descriptor?.uuid) {
            onWrite(gatt, descriptor, status)
        }
    }

    open fun onRead(
        gatt: BluetoothGatt?, descriptor: BluetoothGattDescriptor?, status: Int
    ) {

    }

    open fun onWrite(
        gatt: BluetoothGatt?,
        descriptor: BluetoothGattDescriptor?,
        status: Int
    ) {

    }

    fun write(byteArray: ByteArray) {
        with(bluetoothGatt) {
            desc.value = byteArray
            writeDescriptor(desc)
        }
    }

    fun read() {
        bluetoothGatt.readDescriptor(desc)
    }

}