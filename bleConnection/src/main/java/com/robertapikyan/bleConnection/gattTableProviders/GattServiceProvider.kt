package com.robertapikyan.bleConnection.gattTableProviders

import android.bluetooth.BluetoothGattService

abstract class GattServiceProvider {

    private val _service: BluetoothGattService by lazy {
        onCreateService()
    }

    fun getService() = _service

    protected abstract fun onCreateService(): BluetoothGattService
}