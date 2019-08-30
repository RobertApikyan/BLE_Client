package com.robertapikyan.bleConnection.packageCollector

import com.robertapikyan.bleConnection.utils.Notifier

interface BlePacketCollector {

    interface OnPacketCollectCompleteCallback {
        fun onCollectComplete(
            bytes: ByteArray
        )
    }

    interface OnPacketCollectCallback{
        fun onCollect(bytes: ByteArray)
    }

    fun collect(bytes: ByteArray)

    fun getCollectCompleteNotifier(): Notifier<OnPacketCollectCompleteCallback>

    fun getCollectNotifier():Notifier<OnPacketCollectCallback>
}