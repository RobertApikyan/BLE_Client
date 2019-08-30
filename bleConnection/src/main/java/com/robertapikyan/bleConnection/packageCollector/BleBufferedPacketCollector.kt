package com.robertapikyan.bleConnection.packageCollector

import com.robertapikyan.bleConnection.packageCollector.BlePacketCollector.OnPacketCollectCompleteCallback
import com.robertapikyan.bleConnection.utils.Notifier

class BleBufferedPacketCollector : BlePacketCollector {

    private val collectCompleteNotifier =
        object : Notifier<OnPacketCollectCompleteCallback>(),
            OnPacketCollectCompleteCallback {
            override fun onCollectComplete(bytes: ByteArray) {
                for (listener in getListeners()) {
                    listener.onCollectComplete(
                        bytes
                    )
                }
            }
        }

    private val collectNotifier =
        object : Notifier<BlePacketCollector.OnPacketCollectCallback>(),
            BlePacketCollector.OnPacketCollectCallback {
            override fun onCollect(bytes: ByteArray) {
                for (listener in getListeners()) {
                    listener.onCollect(bytes)
                }
            }
        }

    companion object {
        const val END_OF_PACKAGE: Byte = -1
    }

    private val byteBuffer = mutableListOf<Byte>()

    override fun collect(
        bytes: ByteArray
    ) {
        if (bytes.size == 1 && bytes[0] == END_OF_PACKAGE) {
            collectCompleteNotifier.onCollectComplete(byteBuffer.toByteArray())
            byteBuffer.clear()
        } else {
            byteBuffer.addAll(bytes.asList())
            collectNotifier.onCollect(bytes)
        }
    }

    override fun getCollectCompleteNotifier(): Notifier<OnPacketCollectCompleteCallback> = collectCompleteNotifier

    override fun getCollectNotifier(): Notifier<BlePacketCollector.OnPacketCollectCallback> = collectNotifier
}