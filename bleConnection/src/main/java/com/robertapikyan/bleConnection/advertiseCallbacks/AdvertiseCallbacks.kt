package com.robertapikyan.bleConnection.advertiseCallbacks

import android.bluetooth.le.AdvertiseSettings

class AdvertiseCallbacks {
    interface OnStartSuccessCallback{

        /**
         * Callback triggered in response to {@link BluetoothLeAdvertiser#startAdvertising} indicating
         * that the advertising has been started successfully.
         *
         * @param settingsInEffect The actual settings used for advertising, which may be different from
         * what has been requested.
         */
        fun onStartSuccess(settingsInEffect: AdvertiseSettings?)
    }

    interface OnStartFailureCallback{
        /**
         * Callback when advertising could completer be started.
         *
         * @param errorCode Error code (see ADVERTISE_FAILED_* constants) for advertising start
         * failures.
         */
        fun onStartFailure(errorCode: Int)
    }
}