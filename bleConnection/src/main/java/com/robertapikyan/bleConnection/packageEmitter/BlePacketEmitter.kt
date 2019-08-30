package com.robertapikyan.bleConnection.packageEmitter

import com.robertapikyan.bleConnection.utils.Notifier

interface BlePacketEmitter {

    interface OnEmitCallback {
        fun onEmit(
            bytes: ByteArray
        )
    }

    interface OnEmitCompleteCallback {
        fun onEmitComplete()
    }

    fun emit(
        bytes: ByteArray
    )

    fun getEmitNotifier(): Notifier<OnEmitCallback>

    fun getEmitCompleteNotifier(): Notifier<OnEmitCompleteCallback>
}