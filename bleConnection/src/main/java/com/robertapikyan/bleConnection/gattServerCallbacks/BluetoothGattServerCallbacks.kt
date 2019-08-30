package com.robertapikyan.bleConnection.gattServerCallbacks

import android.bluetooth.*

class BluetoothGattServerCallbacks {
    interface OnConnectionStateChangeCallback {
        /**
         * Callback indicating when a remote device has been connected or disconnected.
         *
         * @param device Remote device that has been connected or disconnected.
         * @param status Status of the connect or disconnect operation.
         * @param newState Returns the new connection state. Can be one of [ ][BluetoothProfile.STATE_DISCONNECTED] or [BluetoothProfile.STATE_CONNECTED]
         */
        fun onConnectionStateChange(
            device: BluetoothDevice?,
            status: Int,
            newState: Int
        )
    }

    interface OnServiceAddedCallback {
        /**
         * Indicates whether a local service has been added successfully.
         *
         * @param status Returns [BluetoothGatt.GATT_SUCCESS] if the service was added
         * successfully.
         * @param service The service that has been added
         */
        fun onServiceAdded(status: Int, service: BluetoothGattService?)
    }

    interface OnCharacteristicReadRequestCallback {
        /**
         * A remote client has requested to read a local characteristic.
         *
         *
         * An application must call [BluetoothGattServer.sendResponse]
         * to complete the request.
         *
         * @param device The remote device that has requested the read operation
         * @param requestId The Id of the request
         * @param offset Offset into the value of the characteristic
         * @param characteristic Characteristic to be read
         */
        fun onCharacteristicReadRequest(
            device: BluetoothDevice?,
            requestId: Int,
            offset: Int,
            characteristic: BluetoothGattCharacteristic?
        )
    }

    interface OnCharacteristicWriteRequestCallback {
        /**
         * A remote client has requested to write to a local characteristic.
         *
         *
         * An application must call [BluetoothGattServer.sendResponse]
         * to complete the request.
         *
         * @param device The remote device that has requested the write operation
         * @param requestId The Id of the request
         * @param characteristic Characteristic to be written to.
         * @param preparedWrite true, if this write operation should be queued for later execution.
         * @param responseNeeded true, if the remote device requires a response
         * @param offset The offset given for the value
         * @param value The value the client wants to assign to the characteristic
         */
        fun onCharacteristicWriteRequest(
            device: BluetoothDevice?,
            requestId: Int,
            characteristic: BluetoothGattCharacteristic?,
            preparedWrite: Boolean,
            responseNeeded: Boolean,
            offset: Int,
            value: ByteArray?
        )
    }

    interface OnDescriptorReadRequestCallback {
        /**
         * A remote client has requested to read a local desc.
         *
         *
         * An application must call [BluetoothGattServer.sendResponse]
         * to complete the request.
         *
         * @param device The remote device that has requested the read operation
         * @param requestId The Id of the request
         * @param offset Offset into the value of the characteristic
         * @param descriptor Descriptor to be read
         */
        fun onDescriptorReadRequest(
            device: BluetoothDevice?,
            requestId: Int,
            offset: Int,
            descriptor: BluetoothGattDescriptor?
        )
    }

    interface OnDescriptorWriteRequestCallback {
        /**
         * A remote client has requested to write to a local desc.
         *
         *
         * An application must call [BluetoothGattServer.sendResponse]
         * to complete the request.
         *
         * @param device The remote device that has requested the write operation
         * @param requestId The Id of the request
         * @param descriptor Descriptor to be written to.
         * @param preparedWrite true, if this write operation should be queued for later execution.
         * @param responseNeeded true, if the remote device requires a response
         * @param offset The offset given for the value
         * @param value The value the client wants to assign to the desc
         */
        fun onDescriptorWriteRequest(
            device: BluetoothDevice?,
            requestId: Int,
            descriptor: BluetoothGattDescriptor?,
            preparedWrite: Boolean,
            responseNeeded: Boolean,
            offset: Int,
            value: ByteArray?
        )
    }

    interface OnExecuteWriteCallback {
        /**
         * Execute all pending write operations for this device.
         *
         *
         * An application must call [BluetoothGattServer.sendResponse]
         * to complete the request.
         *
         * @param device The remote device that has requested the write operations
         * @param requestId The Id of the request
         * @param execute Whether the pending writes should be executed (true) or cancelled (false)
         */
        fun onExecuteWrite(device: BluetoothDevice?, requestId: Int, execute: Boolean)
    }

    interface OnNotificationSentCallback {
        /**
         * Callback invoked when a notification or indication has been sent to
         * a remote device.
         *
         *
         * When multiple notifications are to be sent, an application must
         * wait for this callback to be received before sending additional
         * notifications.
         *
         * @param device The remote device the notification has been sent to
         * @param status [BluetoothGatt.GATT_SUCCESS] if the operation was successful
         */
        fun onNotificationSent(device: BluetoothDevice?, status: Int)
    }

    interface OnMtuChangedCallback {
        /**
         * Callback indicating the MTU for a given device connection has changed.
         *
         *
         * This callback will be invoked if a remote client has requested to change
         * the MTU for a given connection.
         *
         * @param device The remote device that requested the MTU change
         * @param mtu The new MTU size
         */
        fun onMtuChanged(device: BluetoothDevice?, mtu: Int)
    }

    interface OnPhyUpdateCallback {

        /**
         * Callback triggered as result of [BluetoothGattServer.setPreferredPhy], or as a result
         * of remote device changing the PHY.
         *
         * @param device The remote device
         * @param txPhy the transmitter PHY in use. One of [BluetoothDevice.PHY_LE_1M], [ ][BluetoothDevice.PHY_LE_2M], and [BluetoothDevice.PHY_LE_CODED]
         * @param rxPhy the receiver PHY in use. One of [BluetoothDevice.PHY_LE_1M], [ ][BluetoothDevice.PHY_LE_2M], and [BluetoothDevice.PHY_LE_CODED]
         * @param status Status of the PHY update operation. [BluetoothGatt.GATT_SUCCESS] if the
         * operation succeeds.
         */
        fun onPhyUpdate(
            device: BluetoothDevice?,
            txPhy: Int,
            rxPhy: Int,
            status: Int
        )
    }

    interface OnPhyReadCallback {
        /**
         * Callback triggered as result of [BluetoothGattServer.readPhy]
         *
         * @param device The remote device that requested the PHY read
         * @param txPhy the transmitter PHY in use. One of [BluetoothDevice.PHY_LE_1M], [ ][BluetoothDevice.PHY_LE_2M], and [BluetoothDevice.PHY_LE_CODED]
         * @param rxPhy the receiver PHY in use. One of [BluetoothDevice.PHY_LE_1M], [ ][BluetoothDevice.PHY_LE_2M], and [BluetoothDevice.PHY_LE_CODED]
         * @param status Status of the PHY read operation. [BluetoothGatt.GATT_SUCCESS] if the
         * operation succeeds.
         */
        fun onPhyRead(
            device: BluetoothDevice?,
            txPhy: Int,
            rxPhy: Int,
            status: Int
        )
    }

    interface OnConnectionUpdatedCallback {
        /**
         * Callback indicating the connection parameters were updated.
         *
         * @param device The remote device involved
         * @param interval Connection interval used on this connection, 1.25ms unit. Valid range is from
         * 6 (7.5ms) to 3200 (4000ms).
         * @param latency Slave latency for the connection in number of connection events. Valid range
         * is from 0 to 499
         * @param timeout Supervision timeout for this connection, in 10ms unit. Valid range is from 10
         * (0.1s) to 3200 (32s)
         * @param status [BluetoothGatt.GATT_SUCCESS] if the connection has been updated
         * successfully
         * @hide
         */
        fun onConnectionUpdated(
            device: BluetoothDevice?,
            interval: Int,
            latency: Int,
            timeout: Int,
            status: Int
        )
    }

    interface OnCharacteristicChangedCallback {
        /**
         * Callback triggered as a result of a remote characteristic notification.
         *
         * @param gatt GATT client the characteristic is associated with
         * @param characteristic Characteristic that has been updated as a result of a remote
         * notification event.
         */
        fun onCharacteristicChanged(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?
        )
    }
}