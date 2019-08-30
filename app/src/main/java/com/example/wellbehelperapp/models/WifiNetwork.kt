package com.example.wellbehelperapp.models

import android.net.wifi.ScanResult

data class WifiNetwork(
    val ssid: String,
    val bssid: String,
    val level: String,
    val frequency: String,
    val capabilities: String
) {

    companion object {
        private val SSID_POS = 0
        private val BSSID_POS = 1
        private val LEVEL_POS = 2
        private val FREQUENCY_POS = 3
        private val CAPABILITIES_POS = 4

        fun fromDataString(string: String): WifiNetwork {
            val dataArray = string.split(",")
            return WifiNetwork(
                ssid = dataArray[SSID_POS],
                bssid = dataArray[BSSID_POS],
                level = dataArray[LEVEL_POS],
                frequency = dataArray[FREQUENCY_POS],
                capabilities = dataArray[CAPABILITIES_POS]
            )
        }

        fun toDataString(scanResult: ScanResult) =
            "${scanResult.SSID},${scanResult.BSSID},${scanResult.level},${scanResult.frequency},${scanResult.capabilities}"
    }

    override fun toString() = "$ssid,$bssid,$level,$frequency,$capabilities"
}