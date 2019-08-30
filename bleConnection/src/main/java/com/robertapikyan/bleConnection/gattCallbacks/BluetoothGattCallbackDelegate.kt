package com.robertapikyan.bleConnection.gattCallbacks

import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import com.robertapikyan.bleConnection.gattCallbacks.BluetoothGattNotifiers.*

class BluetoothGattCallbackDelegate : BluetoothGattCallback() {

    val phyUpdateNotifier = OnPhyUpdateCallbackNotifier()
    val phyReadNotifier = OnPhyReadCallbackNotifier()
    val connectionStateChangeNotifier = OnConnectionStateChangeCallbackNotifier()
    val serviceDiscoveredNotifier = OnServicesDiscoveredCallbackNotifier()
    val characteristicReadNotifier = OnCharacteristicReadCallbackNotifier()
    val characteristicWriteNotifier = OnCharacteristicWriteCallbackNotifier()
    val characteristicChangedNotifier = OnCharacteristicChangedCallbackNotifier()
    val descriptorReadNotifier = OnDescriptorReadCallbackNotifier()
    val descriptorWriteNotifier = OnDescriptorWriteCallbackNotifier()
    val reliableWriteCompleteNotifier = OnReliableWriteCompleteCallbackNotifier()
    val readRemoteRssiNotifier = OnReadRemoteRssiCallbackNotifier()
    val mtuChangedNotifier = OnMtuChangedCallbackNotifier()

    override fun onPhyUpdate(gatt: BluetoothGatt?, txPhy: Int, rxPhy: Int, status: Int) {
        phyUpdateNotifier.onPhyUpdate(gatt, txPhy, rxPhy, status)
    }

    override fun onPhyRead(gatt: BluetoothGatt?, txPhy: Int, rxPhy: Int, status: Int) {
        phyReadNotifier.onPhyRead(gatt, txPhy, rxPhy, status)
    }

    override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
        connectionStateChangeNotifier.onConnectionStateChange(gatt, status, newState)
    }

    override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
        serviceDiscoveredNotifier.onServicesDiscovered(gatt, status)
    }

    override fun onCharacteristicRead(
        gatt: BluetoothGatt?,
        characteristic: BluetoothGattCharacteristic?,
        status: Int
    ) {
        characteristicReadNotifier.onCharacteristicRead(gatt, characteristic, status)
    }

    override fun onCharacteristicWrite(
        gatt: BluetoothGatt?,
        characteristic: BluetoothGattCharacteristic?,
        status: Int
    ) {
        characteristicWriteNotifier.onCharacteristicWrite(gatt, characteristic, status)
    }

    override fun onCharacteristicChanged(
        gatt: BluetoothGatt?,
        characteristic: BluetoothGattCharacteristic?
    ) {
        characteristicChangedNotifier.onCharacteristicChanged(gatt, characteristic)
    }

    override fun onDescriptorRead(
        gatt: BluetoothGatt?,
        descriptor: BluetoothGattDescriptor?,
        status: Int
    ) {
        descriptorReadNotifier.onDescriptorRead(gatt, descriptor, status)
    }

    override fun onDescriptorWrite(
        gatt: BluetoothGatt?,
        descriptor: BluetoothGattDescriptor?,
        status: Int
    ) {
        descriptorWriteNotifier.onDescriptorWrite(gatt, descriptor, status)
    }

    override fun onReliableWriteCompleted(gatt: BluetoothGatt?, status: Int) {
        reliableWriteCompleteNotifier.onReliableWriteCompleted(gatt, status)
    }

    override fun onReadRemoteRssi(gatt: BluetoothGatt?, rssi: Int, status: Int) {
        readRemoteRssiNotifier.onReadRemoteRssi(gatt, rssi, status)
    }

    override fun onMtuChanged(gatt: BluetoothGatt?, mtu: Int, status: Int) {
        mtuChangedNotifier.onMtuChanged(gatt, mtu, status)
    }

    fun removeAll() {
        phyUpdateNotifier.removeAll()
        phyReadNotifier.removeAll()
        connectionStateChangeNotifier.removeAll()
        serviceDiscoveredNotifier.removeAll()
        characteristicReadNotifier.removeAll()
        characteristicWriteNotifier.removeAll()
        characteristicChangedNotifier.removeAll()
        descriptorReadNotifier.removeAll()
        descriptorWriteNotifier.removeAll()
        reliableWriteCompleteNotifier.removeAll()
        readRemoteRssiNotifier.removeAll()
        mtuChangedNotifier.removeAll()
    }
}