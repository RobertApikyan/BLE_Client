package com.robertapikyan.bleConnection.gattServices

import android.bluetooth.BluetoothGattService

abstract class GattServiceLazyProvider {

    private val service: BluetoothGattService by lazy {
        onCreateService()
    }

    fun get() = service

    protected abstract fun onCreateService(): BluetoothGattService
}