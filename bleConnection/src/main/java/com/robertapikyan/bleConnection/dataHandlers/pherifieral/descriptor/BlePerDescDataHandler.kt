package com.robertapikyan.bleConnection.dataHandlers.pherifieral.descriptor

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGattDescriptor
import android.bluetooth.BluetoothGattServer
import com.robertapikyan.bleConnection.gattServerCallbacks.BluetoothGattServerCallbacks

open class BlePerDescDataHandler
    : BluetoothGattServerCallbacks.OnDescriptorReadRequestCallback,
    BluetoothGattServerCallbacks.OnDescriptorWriteRequestCallback {

    protected lateinit var descriptor: BluetoothGattDescriptor
    protected lateinit var gattServer: BluetoothGattServer

    fun setupWithDescriptor(descriptor: BluetoothGattDescriptor) {
        this.descriptor = descriptor
    }

    fun setupWithGattServer(gattServer: BluetoothGattServer) {
        this.gattServer = gattServer
    }


    final override fun onDescriptorReadRequest(
        device: BluetoothDevice?,
        requestId: Int,
        offset: Int,
        descriptor: BluetoothGattDescriptor?
    ) {
        if (this.descriptor.uuid == descriptor?.uuid) {
            onReadRequest(device, requestId, offset, descriptor)
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
        if (this.descriptor.uuid == descriptor?.uuid) {
            onWriteRequest(device, requestId, descriptor, preparedWrite, responseNeeded, offset, value)
        }
    }

    protected open fun onReadRequest(
        device: BluetoothDevice?,
        requestId: Int,
        offset: Int,
        descriptor: BluetoothGattDescriptor?
    ) {

    }

    open fun onWriteRequest(
        device: BluetoothDevice?,
        requestId: Int,
        descriptor: BluetoothGattDescriptor?,
        preparedWrite: Boolean,
        responseNeeded: Boolean,
        offset: Int,
        value: ByteArray?
    ) {

    }

    open fun sendResponse(device: BluetoothDevice?, requestId: Int, status: Int, offset: Int, byteArray: ByteArray) {
        gattServer.sendResponse(device, requestId, status, offset, byteArray)
    }
}