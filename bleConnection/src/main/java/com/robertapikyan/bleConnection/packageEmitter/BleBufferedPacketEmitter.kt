package com.robertapikyan.bleConnection.packageEmitter

import com.robertapikyan.bleConnection.packageCollector.BleBufferedPacketCollector.Companion.END_OF_PACKAGE
import com.robertapikyan.bleConnection.packageEmitter.BlePacketEmitter.OnEmitCallback
import com.robertapikyan.bleConnection.packageEmitter.BlePacketEmitter.OnEmitCompleteCallback
import com.robertapikyan.bleConnection.utils.Notifier
import java.util.*

class BleBufferedPacketEmitter(
    private val maxPacketSize: Int = MAX_BYTES_SIZE
) : BlePacketEmitter {

    companion object {
        private const val MAX_BYTES_SIZE = 20
    }

    private val emitNotifier = object : Notifier<OnEmitCallback>(), OnEmitCallback {
        override fun onEmit(bytes: ByteArray) {
            for (listener in getListeners()) {
                listener.onEmit(bytes)
            }
        }
    }

    private val emitCompleteNotifier = object : Notifier<OnEmitCompleteCallback>(),
        OnEmitCompleteCallback {
        override fun onEmitComplete() {
            for (listener in getListeners()) {
                listener.onEmitComplete()
            }
        }
    }

    override fun getEmitNotifier(): Notifier<OnEmitCallback> = emitNotifier

    override fun getEmitCompleteNotifier(): Notifier<OnEmitCompleteCallback> = emitCompleteNotifier

    override fun emit(
        bytes: ByteArray
    ) {
        val packetsCount = Math.ceil(bytes.size.toDouble() / maxPacketSize.toDouble()).toInt()

        for (packetIndex in 0 until packetsCount) {

            val start = packetIndex * maxPacketSize

            val end = if (start + maxPacketSize < bytes.size) {
                start + maxPacketSize
            } else {
                bytes.size
            }

            val packet = Arrays.copyOfRange(bytes, start, end)

            emitNotifier.onEmit(
                packet
            )
            Thread.sleep(200)
        }

        // emit the last byte
        emitNotifier.onEmit(
            byteArrayOf(END_OF_PACKAGE)
        )
    }
}