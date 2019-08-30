package com.robertapikyan.bleConnection.dataHandlers.client.characteristic

import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCharacteristic
import com.robertapikyan.bleConnection.gattCallbacks.BluetoothGattCallbacks

open class BleClientCharDataHandler : BluetoothGattCallbacks.OnCharacteristicChangedCallback,
    BluetoothGattCallbacks.OnCharacteristicReadCallback,
    BluetoothGattCallbacks.OnCharacteristicWriteCallback {

    protected lateinit var characteristic: BluetoothGattCharacteristic
    protected lateinit var bluetoothGatt: BluetoothGatt

    fun setupWithCharacteristic(characteristic: BluetoothGattCharacteristic) {
        this.characteristic = characteristic
    }

    fun setupWithGatt(bluetoothGatt: BluetoothGatt){
        this.bluetoothGatt = bluetoothGatt
    }

    final override fun onCharacteristicWrite(
        gatt: BluetoothGatt?,
        characteristic: BluetoothGattCharacteristic?,
        status: Int
    ) {
        if (this.characteristic.uuid == characteristic?.uuid) {
            onWrite(gatt, characteristic, status)
        }
    }

    final override fun onCharacteristicRead(
        gatt: BluetoothGatt?,
        characteristic: BluetoothGattCharacteristic?,
        status: Int
    ) {
        if (this.characteristic.uuid == characteristic?.uuid) {
            onRead(gatt, characteristic, status)
        }
    }

    override fun onCharacteristicChanged(
        gatt: BluetoothGatt?,
        characteristic: BluetoothGattCharacteristic?
    ) {
        if (this.characteristic.uuid == characteristic?.uuid) {
            onChanged(gatt, characteristic)
        }
    }

    fun write(byteArray: ByteArray) = with(bluetoothGatt) {
        characteristic.value = byteArray
        writeCharacteristic(characteristic)
    }

    fun read() {
        bluetoothGatt.readCharacteristic(characteristic)
    }

    open fun onChanged(
        gatt: BluetoothGatt?,
        characteristic: BluetoothGattCharacteristic?
    ) {

    }

    open fun onRead(
        gatt: BluetoothGatt?,
        characteristic: BluetoothGattCharacteristic?,
        status: Int
    ) {

    }

    open fun onWrite(
        gatt: BluetoothGatt?,
        characteristic: BluetoothGattCharacteristic?,
        status: Int
    ) {

    }
}