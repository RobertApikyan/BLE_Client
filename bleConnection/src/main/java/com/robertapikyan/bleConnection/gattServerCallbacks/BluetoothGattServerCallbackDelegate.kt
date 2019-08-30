package com.robertapikyan.bleConnection.gattServerCallbacks

import android.bluetooth.*
import com.robertapikyan.bleConnection.gattServerCallbacks.BluetoothGattServerNotifiers.*

class BluetoothGattServerCallbackDelegate : BluetoothGattServerCallback() {

    val connectionStateChangeNotifier = OnConnectionStateChangeNotifier()
    val serviceAddedNotifier = OnServiceAddedNotifier()
    val characteristicReadRequestNotifier = OnCharacteristicReadRequestNotifier()
    val characteristicWriteRequestNotifier = OnCharacteristicWriteRequestNotifier()
    val descriptorReadRequestNotifier = OnDescriptorReadRequestNotifier()
    val descriptorWriteRequestNotifier = OnDescriptorWriteRequestNotifier()
    val executeWriteNotifier = OnExecuteWriteNotifier()
    val notificationSentNotifier = OnNotificationSentNotifier()
    val mtuChangedNotifier = OnMtuChangedNotifier()
    val phyUpdateNotifier = OnPhyUpdateNotifier()
    val phyReadNotifier = OnPhyReadNotifier()

    override fun onConnectionStateChange(device: BluetoothDevice, status: Int, newState: Int) {
        connectionStateChangeNotifier.onConnectionStateChange(device, status, newState)
    }

    override fun onServiceAdded(status: Int, service: BluetoothGattService) {
        serviceAddedNotifier.onServiceAdded(status, service)
    }

    override fun onCharacteristicReadRequest(
        device: BluetoothDevice?,
        requestId: Int,
        offset: Int,
        characteristic: BluetoothGattCharacteristic?
    ) {
        characteristicReadRequestNotifier.onCharacteristicReadRequest(device, requestId, offset, characteristic)
    }

    override fun onCharacteristicWriteRequest(
        device: BluetoothDevice?,
        requestId: Int,
        characteristic: BluetoothGattCharacteristic?,
        preparedWrite: Boolean,
        responseNeeded: Boolean,
        offset: Int,
        value: ByteArray?
    ) {
        characteristicWriteRequestNotifier.onCharacteristicWriteRequest(
            device,
            requestId,
            characteristic,
            preparedWrite,
            responseNeeded,
            offset,
            value
        )
    }

    override fun onDescriptorReadRequest(
        device: BluetoothDevice?,
        requestId: Int,
        offset: Int,
        descriptor: BluetoothGattDescriptor?
    ) {
        descriptorReadRequestNotifier.onDescriptorReadRequest(device, requestId, offset, descriptor)
    }

    override fun onDescriptorWriteRequest(
        device: BluetoothDevice?,
        requestId: Int,
        descriptor: BluetoothGattDescriptor?,
        preparedWrite: Boolean,
        responseNeeded: Boolean,
        offset: Int,
        value: ByteArray?
    ) {
        descriptorWriteRequestNotifier
            .onDescriptorWriteRequest(device, requestId, descriptor, preparedWrite, responseNeeded, offset, value)
    }

    override fun onExecuteWrite(device: BluetoothDevice?, requestId: Int, execute: Boolean) {
        executeWriteNotifier.onExecuteWrite(device, requestId, execute)
    }

    override fun onNotificationSent(device: BluetoothDevice?, status: Int) {
        notificationSentNotifier.onNotificationSent(device, status)
    }

    override fun onMtuChanged(device: BluetoothDevice?, mtu: Int) {
        mtuChangedNotifier.onMtuChanged(device, mtu)
    }

    override fun onPhyUpdate(device: BluetoothDevice?, txPhy: Int, rxPhy: Int, status: Int) {
        phyUpdateNotifier.onPhyUpdate(device, txPhy, rxPhy, status)
    }

    override fun onPhyRead(device: BluetoothDevice?, txPhy: Int, rxPhy: Int, status: Int) {
        phyReadNotifier.onPhyRead(device, txPhy, rxPhy, status)
    }

    fun removeAll() {
        connectionStateChangeNotifier.removeAll()
        serviceAddedNotifier.removeAll()
        characteristicReadRequestNotifier.removeAll()
        characteristicWriteRequestNotifier.removeAll()
        descriptorReadRequestNotifier.removeAll()
        descriptorWriteRequestNotifier.removeAll()
        executeWriteNotifier.removeAll()
        notificationSentNotifier.removeAll()
        mtuChangedNotifier.removeAll()
        phyUpdateNotifier.removeAll()
        phyReadNotifier.removeAll()
    }
}