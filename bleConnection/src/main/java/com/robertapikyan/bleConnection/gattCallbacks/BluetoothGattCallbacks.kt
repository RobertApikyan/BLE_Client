package com.robertapikyan.bleConnection.gattCallbacks

import android.bluetooth.*

class BluetoothGattCallbacks {

    interface OnPhyUpdateCallback {
        /**
         * Callback triggered as result of [BluetoothGatt.setPreferredPhy], or as a result of
         * remote device changing the PHY.
         *
         * @param gatt GATT client
         * @param txPhy the transmitter PHY in use. One of [BluetoothDevice.PHY_LE_1M], [ ][BluetoothDevice.PHY_LE_2M], and [BluetoothDevice.PHY_LE_CODED].
         * @param rxPhy the receiver PHY in use. One of [BluetoothDevice.PHY_LE_1M], [ ][BluetoothDevice.PHY_LE_2M], and [BluetoothDevice.PHY_LE_CODED].
         * @param status Status of the PHY update operation. [BluetoothGatt.GATT_SUCCESS] if the
         * operation succeeds.
         */
        fun onPhyUpdate(gatt: BluetoothGatt?, txPhy: Int, rxPhy: Int, status: Int)
    }

    interface OnPhyReadCallback {
        /**
         * Callback triggered as result of [BluetoothGatt.readPhy]
         *
         * @param gatt GATT client
         * @param txPhy the transmitter PHY in use. One of [BluetoothDevice.PHY_LE_1M], [ ][BluetoothDevice.PHY_LE_2M], and [BluetoothDevice.PHY_LE_CODED].
         * @param rxPhy the receiver PHY in use. One of [BluetoothDevice.PHY_LE_1M], [ ][BluetoothDevice.PHY_LE_2M], and [BluetoothDevice.PHY_LE_CODED].
         * @param status Status of the PHY read operation. [BluetoothGatt.GATT_SUCCESS] if the
         * operation succeeds.
         */
        fun onPhyRead(gatt: BluetoothGatt?, txPhy: Int, rxPhy: Int, status: Int)
    }

    interface OnConnectionStateChangCallback {
        /**
         * Callback indicating when GATT client has connected/disconnected to/from a remote
         * GATT server.
         *
         * @param gatt GATT client
         * @param status Status of the connect or disconnect operation. [ ][BluetoothGatt.GATT_SUCCESS] if the operation succeeds.
         * @param newState Returns the new connection state. Can be one of [ ][BluetoothProfile.STATE_DISCONNECTED] or [BluetoothProfile.STATE_CONNECTED]
         */
        fun onConnectionStateChange(
            gatt: BluetoothGatt?, status: Int,
            newState: Int
        )
    }

    interface OnServicesDiscoveredCallback {
        /**
         * Callback invoked when the list of remote services, characteristics and descriptors
         * for the remote device have been updated, ie new services have been discovered.
         *
         * @param gatt GATT client invoked [BluetoothGatt.discoverServices]
         * @param status [BluetoothGatt.GATT_SUCCESS] if the remote device has been explored
         * successfully.
         */
        fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int)
    }

    interface OnCharacteristicReadCallback {
        /**
         * Callback reporting the result of a characteristic read operation.
         *
         * @param gatt GATT client invoked [BluetoothGatt.readCharacteristic]
         * @param characteristic Characteristic that was read from the associated remote device.
         * @param status [BluetoothGatt.GATT_SUCCESS] if the read operation was completed
         * successfully.
         */
        fun onCharacteristicRead(
            gatt: BluetoothGatt?, characteristic: BluetoothGattCharacteristic?,
            status: Int
        )
    }

    interface OnCharacteristicWriteCallback {
        /**
         * Callback indicating the result of a characteristic write operation.
         *
         *
         * If this callback is invoked while a reliable write transaction is
         * in progress, the value of the characteristic represents the value
         * reported by the remote device. An application should compare this
         * value to the desired value to be written. If the values don't match,
         * the application must abort the reliable write transaction.
         *
         * @param gatt GATT client invoked [BluetoothGatt.writeCharacteristic]
         * @param characteristic Characteristic that was written to the associated remote device.
         * @param status The result of the write operation [BluetoothGatt.GATT_SUCCESS] if the
         * operation succeeds.
         */
        fun onCharacteristicWrite(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?, status: Int
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

    interface OnDescriptorReadCallback {
        /**
         * Callback reporting the result of a desc read operation.
         *
         * @param gatt GATT client invoked [BluetoothGatt.readDescriptor]
         * @param descriptor Descriptor that was read from the associated remote device.
         * @param status [BluetoothGatt.GATT_SUCCESS] if the read operation was completed
         * successfully
         */
        fun onDescriptorRead(
            gatt: BluetoothGatt?, descriptor: BluetoothGattDescriptor?,
            status: Int
        )
    }

    interface OnDescriptorWriteCallback {
        /**
         * Callback indicating the result of a desc write operation.
         *
         * @param gatt GATT client invoked [BluetoothGatt.writeDescriptor]
         * @param descriptor Descriptor that was writte to the associated remote device.
         * @param status The result of the write operation [BluetoothGatt.GATT_SUCCESS] if the
         * operation succeeds.
         */
        fun onDescriptorWrite(
            gatt: BluetoothGatt?, descriptor: BluetoothGattDescriptor?,
            status: Int
        )
    }

    interface OnReliableWriteCompleteCallback {
        /**
         * Callback invoked when a reliable write transaction has been completed.
         *
         * @param gatt GATT client invoked [BluetoothGatt.executeReliableWrite]
         * @param status [BluetoothGatt.GATT_SUCCESS] if the reliable write transaction was
         * executed successfully
         */
        fun onReliableWriteCompleted(gatt: BluetoothGatt?, status: Int)
    }

    interface OnReadRemoteRssiCallback {
        /**
         * Callback reporting the RSSI for a remote device connection.
         *
         * This callback is triggered in response to the
         * [BluetoothGatt.readRemoteRssi] function.
         *
         * @param gatt GATT client invoked [BluetoothGatt.readRemoteRssi]
         * @param rssi The RSSI value for the remote device
         * @param status [BluetoothGatt.GATT_SUCCESS] if the RSSI was read successfully
         */
        fun onReadRemoteRssi(gatt: BluetoothGatt?, rssi: Int, status: Int)
    }

    interface OnMtuChangedCallback {
        /**
         * Callback indicating the MTU for a given device connection has changed.
         *
         * This callback is triggered in response to the
         * [BluetoothGatt.requestMtu] function, or in response to a connection
         * event.
         *
         * @param gatt GATT client invoked [BluetoothGatt.requestMtu]
         * @param mtu The new MTU size
         * @param status [BluetoothGatt.GATT_SUCCESS] if the MTU has been changed successfully
         */
        fun onMtuChanged(gatt: BluetoothGatt?, mtu: Int, status: Int)
    }
}