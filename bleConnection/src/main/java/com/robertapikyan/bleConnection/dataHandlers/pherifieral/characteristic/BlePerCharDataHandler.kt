package com.robertapikyan.bleConnection.dataHandlers.pherifieral.characteristic

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattServer
import com.robertapikyan.bleConnection.gattServerCallbacks.BluetoothGattServerCallbacks

open class BlePerCharDataHandler : BluetoothGattServerCallbacks.OnCharacteristicWriteRequestCallback,
    BluetoothGattServerCallbacks.OnCharacteristicReadRequestCallback {

    protected lateinit var characteristic: BluetoothGattCharacteristic
    protected lateinit var gattServer: BluetoothGattServer

    fun setupWithCharacteristic(characteristic: BluetoothGattCharacteristic) {
        this.characteristic = characteristic
    }

    fun setupWithGattServer(gattServer: BluetoothGattServer){
        this.gattServer = gattServer
    }

    final override fun onCharacteristicReadRequest(
        device: BluetoothDevice?,
        requestId: Int,
        offset: Int,
        characteristic: BluetoothGattCharacteristic?
    ) {
        if (this.characteristic.uuid == characteristic?.uuid) {
            onReadRequest(device, requestId, offset, characteristic)
        }
    }

    final override fun onCharacteristicWriteRequest(
        device: BluetoothDevice?,
        requestId: Int,
        characteristic: BluetoothGattCharacteristic?,
        preparedWrite: Boolean,
        responseNeeded: Boolean,
        offset: Int,
        value: ByteArray?
    ) {
        if (this.characteristic.uuid == characteristic?.uuid) {
            onWriteRequest(device, requestId, characteristic, preparedWrite, responseNeeded, offset, value)
        }
    }

    protected open fun onReadRequest(
        device: BluetoothDevice?,
        requestId: Int,
        offset: Int,
        characteristic: BluetoothGattCharacteristic?
    ) {

    }

    protected open fun onWriteRequest(
        device: BluetoothDevice?,
        requestId: Int,
        characteristic: BluetoothGattCharacteristic?,
        preparedWrite: Boolean,
        responseNeeded: Boolean,
        offset: Int,
        value: ByteArray?
    ) {

    }

    fun notifyData(device: BluetoothDevice, byteArray: ByteArray) {
        characteristic.value = byteArray
        gattServer.notifyCharacteristicChanged(device, characteristic, false)
    }

    open fun sendResponse(device: BluetoothDevice?, requestId: Int, status: Int, offset: Int, byteArray: ByteArray) {
        gattServer.sendResponse(device, requestId, status, offset, byteArray)
    }
}