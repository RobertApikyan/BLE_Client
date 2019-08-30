package com.robertapikyan.bleConnection.advertiseCallbacks

import android.bluetooth.le.AdvertiseSettings
import com.robertapikyan.bleConnection.utils.Notifier

class AdvertiseCallbackNotifiers {

    class OnStartSuccessCallbackNotifier
        : Notifier<AdvertiseCallbacks.OnStartSuccessCallback>(),
        AdvertiseCallbacks.OnStartSuccessCallback {
        override fun onStartSuccess(settingsInEffect: AdvertiseSettings?) {
            for (listener in getListeners()) {
                listener.onStartSuccess(settingsInEffect)
            }
        }
    }

    class OnStartFailureCallbackNotifier :
        Notifier<AdvertiseCallbacks.OnStartFailureCallback>(),
        AdvertiseCallbacks.OnStartFailureCallback {
        override fun onStartFailure(errorCode: Int) {
            for (listener in getListeners()) {
                listener.onStartFailure(errorCode)
            }
        }
    }
}