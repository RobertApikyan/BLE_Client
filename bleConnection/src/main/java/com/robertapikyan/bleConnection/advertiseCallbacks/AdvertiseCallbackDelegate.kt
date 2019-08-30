package com.robertapikyan.bleConnection.advertiseCallbacks

import android.bluetooth.le.AdvertiseCallback
import android.bluetooth.le.AdvertiseSettings

class AdvertiseCallbackDelegate: AdvertiseCallback() {

    val startSuccessCallbackNotifier =
        AdvertiseCallbackNotifiers.OnStartSuccessCallbackNotifier()
    val startFailedCallbackNotifier =
        AdvertiseCallbackNotifiers.OnStartFailureCallbackNotifier()

    override fun onStartSuccess(settingsInEffect: AdvertiseSettings?) {
        startSuccessCallbackNotifier.onStartSuccess(settingsInEffect)
    }

    override fun onStartFailure(errorCode: Int) {
        startFailedCallbackNotifier.onStartFailure(errorCode)
    }
}