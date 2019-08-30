package com.robertapikyan.bleConnection.gattServerCallbacks

import android.bluetooth.*
import com.robertapikyan.bleConnection.utils.Notifier
import com.robertapikyan.bleConnection.gattServerCallbacks.BluetoothGattServerCallbacks.*

class BluetoothGattServerNotifiers {

    class OnConnectionStateChangeNotifier :
        Notifier<OnConnectionStateChangeCallback>(),
        OnConnectionStateChangeCallback {

        override fun onConnectionStateChange(device: BluetoothDevice?, status: Int, newState: Int) {
            for (listener in getListeners()) {
                listener.onConnectionStateChange(device, status, newState)
            }
        }
    }

    class OnServiceAddedNotifier : Notifier<OnServiceAddedCallback>(),
        OnServiceAddedCallback {

        override fun onServiceAdded(status: Int, service: BluetoothGattService?) {
            for (listener in getListeners()) {
                listener.onServiceAdded(status, service)
            }
        }
    }

    class OnCharacteristicReadRequestNotifier :
        Notifier<OnCharacteristicReadRequestCallback>(),
        OnCharacteristicReadRequestCallback {

        override fun onCharacteristicReadRequest(
            device: BluetoothDevice?,
            requestId: Int,
            offset: Int,
            characteristic: BluetoothGattCharacteristic?
        ) {
            for (listener in getListeners()) {
                listener.onCharacteristicReadRequest(device, requestId, offset, characteristic)
            }
        }
    }

    class OnCharacteristicWriteRequestNotifier :
        Notifier<OnCharacteristicWriteRequestCallback>(),
        OnCharacteristicWriteRequestCallback {
        override fun onCharacteristicWriteRequest(
            device: BluetoothDevice?,
            requestId: Int,
            characteristic: BluetoothGattCharacteristic?,
            preparedWrite: Boolean,
            responseNeeded: Boolean,
            offset: Int,
            value: ByteArray?
        ) {
            for (listener in getListeners()) {
                listener.onCharacteristicWriteRequest(
                    device,
                    requestId,
                    characteristic,
                    preparedWrite,
                    responseNeeded,
                    offset,
                    value
                )
            }
        }
    }

    class OnDescriptorReadRequestNotifier :
        Notifier<OnDescriptorReadRequestCallback>(),
        OnDescriptorReadRequestCallback {
        override fun onDescriptorReadRequest(
            device: BluetoothDevice?,
            requestId: Int,
            offset: Int,
            descriptor: BluetoothGattDescriptor?
        ) {
            for (listener in getListeners()) {
                listener.onDescriptorReadRequest(device, requestId, offset, descriptor)
            }
        }
    }

    class OnDescriptorWriteRequestNotifier :
        Notifier<OnDescriptorWriteRequestCallback>(),
        OnDescriptorWriteRequestCallback {
        override fun onDescriptorWriteRequest(
            device: BluetoothDevice?,
            requestId: Int,
            descriptor: BluetoothGattDescriptor?,
            preparedWrite: Boolean,
            responseNeeded: Boolean,
            offset: Int,
            value: ByteArray?
        ) {
            for (listener in getListeners()) {
                listener.onDescriptorWriteRequest(
                    device,
                    requestId,
                    descriptor,
                    preparedWrite,
                    responseNeeded,
                    offset,
                    value
                )
            }
        }
    }

    class OnExecuteWriteNotifier :
        Notifier<OnExecuteWriteCallback>(),
        OnExecuteWriteCallback {
        override fun onExecuteWrite(device: BluetoothDevice?, requestId: Int, execute: Boolean) {
            for (listener in getListeners()) {
                listener.onExecuteWrite(device, requestId, execute)
            }
        }
    }

    class OnNotificationSentNotifier :
        Notifier<OnNotificationSentCallback>(),
        OnNotificationSentCallback {
        override fun onNotificationSent(device: BluetoothDevice?, status: Int) {
            for (listener in getListeners()) {
                listener.onNotificationSent(device, status)
            }
        }
    }

    class OnMtuChangedNotifier : Notifier<OnMtuChangedCallback>(),
        OnMtuChangedCallback {
        override fun onMtuChanged(device: BluetoothDevice?, mtu: Int) {
            for (listener in getListeners()) {
                listener.onMtuChanged(device, mtu)
            }
        }
    }

    class OnPhyUpdateNotifier : Notifier<OnPhyUpdateCallback>(),
        OnPhyUpdateCallback {
        override fun onPhyUpdate(device: BluetoothDevice?, txPhy: Int, rxPhy: Int, status: Int) {
            for (listener in getListeners()) {
                listener.onPhyUpdate(device,txPhy,rxPhy,status)
            }
        }
    }

    class OnPhyReadNotifier : Notifier<OnPhyReadCallback>(),
        OnPhyReadCallback {
        override fun onPhyRead(device: BluetoothDevice?, txPhy: Int, rxPhy: Int, status: Int) {
            for (listener in getListeners()) {
                listener.onPhyRead(device, txPhy, rxPhy, status)
            }
        }
    }

    class OnConnectionUpdateNotifier : Notifier<OnConnectionUpdatedCallback>(),
        OnConnectionUpdatedCallback {
        override fun onConnectionUpdated(
            device: BluetoothDevice?,
            interval: Int,
            latency: Int,
            timeout: Int,
            status: Int
        ) {
            for (listener in getListeners()) {
                listener.onConnectionUpdated(device, interval, latency, timeout, status)
            }
        }
    }

    class OnCharacteristicChangedCallbackNotifier: Notifier<OnCharacteristicChangedCallback>(),
        OnCharacteristicChangedCallback {
        override fun onCharacteristicChanged(gatt: BluetoothGatt?, characteristic: BluetoothGattCharacteristic?) {
            for (listener in getListeners()) {
                listener.onCharacteristicChanged(gatt,characteristic)
            }
        }
    }
}