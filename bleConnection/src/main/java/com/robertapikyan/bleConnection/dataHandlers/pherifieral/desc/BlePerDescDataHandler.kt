package com.robertapikyan.bleConnection.dataHandlers.pherifieral.desc

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGattDescriptor
import android.bluetooth.BluetoothGattServer
import com.robertapikyan.bleConnection.gattServerCallbacks.BluetoothGattServerCallbacks

open class BlePerDescDataHandler(protected val descriptor:BluetoothGattDescriptor,
                            protected val gattServer: BluetoothGattServer)
    :BluetoothGattServerCallbacks.OnDescriptorReadRequestCallback,
    BluetoothGattServerCallbacks.OnDescriptorWriteRequestCallback{

    final override fun onDescriptorReadRequest(
        device: BluetoothDevice?,
        requestId: Int,
        offset: Int,
        descriptor: BluetoothGattDescriptor?
    ) {
        if (this.descriptor.uuid == descriptor?.uuid){
            onReadRequest(device,requestId,offset,descriptor)
        }
    }

    final override fun onDescriptorWriteRequest(
        device: BluetoothDevice?,
        requestId: Int,
        descriptor: BluetoothGattDescriptor?,
        preparedWrite: Boolean,
        responseNeeded: Boolean,
        offset: Int,
        value: ByteArray?
    ) {
        if (this.descriptor.uuid == descriptor?.uuid){
            onWriteRequest(device,requestId,descriptor,preparedWrite,responseNeeded,offset,value)
        }
    }

    protected open fun onReadRequest(
        device: BluetoothDevice?,
        requestId: Int,
        offset: Int,
        descriptor: BluetoothGattDescriptor?
    ){

    }

    protected open fun onWriteRequest(
        device: BluetoothDevice?,
        requestId: Int,
        descriptor: BluetoothGattDescriptor?,
        preparedWrite: Boolean,
        responseNeeded: Boolean,
        offset: Int,
        value: ByteArray?
    ){

    }
}