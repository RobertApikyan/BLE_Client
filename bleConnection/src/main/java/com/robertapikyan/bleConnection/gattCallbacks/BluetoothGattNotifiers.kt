package com.robertapikyan.bleConnection.gattCallbacks

import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import com.robertapikyan.bleConnection.gattCallbacks.BluetoothGattCallbacks.*
import com.robertapikyan.bleConnection.utils.Notifier

class BluetoothGattNotifiers {

    class OnPhyUpdateCallbackNotifier :
        Notifier<OnPhyUpdateCallback>(),
        OnPhyUpdateCallback {
        override fun onPhyUpdate(gatt: BluetoothGatt?, txPhy: Int, rxPhy: Int, status: Int) {
            for (listener in getListeners()) {
                listener.onPhyUpdate(gatt, txPhy, rxPhy, status)
            }
        }
    }

    class OnPhyReadCallbackNotifier :
        Notifier<OnPhyReadCallback>(),
        OnPhyReadCallback {
        override fun onPhyRead(gatt: BluetoothGatt?, txPhy: Int, rxPhy: Int, status: Int) {
            for (listener in getListeners()) {
                listener.onPhyRead(gatt, rxPhy, rxPhy, status)
            }
        }
    }

    class OnConnectionStateChangeCallbackNotifier
        : Notifier<OnConnectionStateChangCallback>(), OnConnectionStateChangCallback {
        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
            for (listener in getListeners()) {
                listener.onConnectionStateChange(gatt, status, newState)
            }
        }
    }

    class OnServicesDiscoveredCallbackNotifier
        : Notifier<OnServicesDiscoveredCallback>(),
        OnServicesDiscoveredCallback {
        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            for (listener in getListeners()) {
                listener.onServicesDiscovered(gatt, status)
            }
        }
    }

    class OnCharacteristicReadCallbackNotifier :
        Notifier<OnCharacteristicReadCallback>(),
        OnCharacteristicReadCallback {
        override fun onCharacteristicRead(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?,
            status: Int
        ) {
            for (listener in getListeners()) {
                listener.onCharacteristicRead(gatt, characteristic, status)
            }
        }
    }

    class OnCharacteristicWriteCallbackNotifier :
        Notifier<OnCharacteristicWriteCallback>(),
        OnCharacteristicWriteCallback {
        override fun onCharacteristicWrite(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?,
            status: Int
        ) {
            for (listener in getListeners()) {
                listener.onCharacteristicWrite(gatt, characteristic, status)
            }
        }
    }

    class OnCharacteristicChangedCallbackNotifier :
        Notifier<OnCharacteristicChangedCallback>(),
        OnCharacteristicChangedCallback {
        override fun onCharacteristicChanged(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?
        ) {
            for (listener in getListeners()) {
                listener.onCharacteristicChanged(gatt, characteristic)
            }
        }
    }

    class OnDescriptorReadCallbackNotifier : Notifier<OnDescriptorReadCallback>(),
        OnDescriptorReadCallback {
        override fun onDescriptorRead(
            gatt: BluetoothGatt?,
            descriptor: BluetoothGattDescriptor?,
            status: Int
        ) {
            for (listener in getListeners()) {
                listener.onDescriptorRead(gatt, descriptor, status)
            }
        }
    }

    class OnDescriptorWriteCallbackNotifier : Notifier<OnDescriptorWriteCallback>(),
        OnDescriptorWriteCallback {
        override fun onDescriptorWrite(
            gatt: BluetoothGatt?,
            descriptor: BluetoothGattDescriptor?,
            status: Int
        ) {
            for (listener in getListeners()) {
                listener.onDescriptorWrite(gatt, descriptor, status)
            }
        }
    }

    class OnReliableWriteCompleteCallbackNotifier : Notifier<OnReliableWriteCompleteCallback>(),
        OnReliableWriteCompleteCallback {
        override fun onReliableWriteCompleted(gatt: BluetoothGatt?, status: Int) {
            for (listener in getListeners()) {
                listener.onReliableWriteCompleted(gatt, status)
            }
        }
    }

    class OnReadRemoteRssiCallbackNotifier : Notifier<OnReadRemoteRssiCallback>(),
        OnReadRemoteRssiCallback {
        override fun onReadRemoteRssi(gatt: BluetoothGatt?, rssi: Int, status: Int) {
            for (listener in getListeners()) {
                listener.onReadRemoteRssi(gatt, rssi, status)
            }
        }
    }

    class OnMtuChangedCallbackNotifier : Notifier<OnMtuChangedCallback>(),
        OnMtuChangedCallback {
        override fun onMtuChanged(gatt: BluetoothGatt?, mtu: Int, status: Int) {
            for (listener in getListeners()) {
                listener.onMtuChanged(gatt, mtu, status)
            }
        }
    }

}

