package com.example.wellbehelperapp

import java.util.*

object BleUUIDs {

    // Watch Service
    val WATCH_SERVICE = convertFromInteger(0xFFFA)


    ////////////////////////////////////////////////////WIFI CONFIG////////////////////////////////////////////////////

    // read: on read will start to notify available wifi networks list
    // format: "SSID,BSSID,level,frequency,capabilities|SSID,BSSID,level,frequency,capabilities|SSID,BSSID,level..."
    val WIFI_CONFIG_AVAILABLE_NETWORKS_CHARACTERISTIC = UUID.fromString("57263864-9a8d-4300-8100-e725953fcf50")

    // read: on read will return connected = 1 if device is connected to wifi network otherwise 0
    // write: on write request, device will try to connect to wifi network with specified bssid and password
    // format: write -> bssid;pass
    val WIFI_CONFIG_SSID_PASS_DESCRIPTOR =  UUID.fromString("e6ae1c25-6584-4b20-a97b-31b246c0dd2d")

    // read: on read will return wherever wifi is enabled = 1 or disabled = 0
    // write: on write will enable/disable wifi, enable = 1, disable = 0
    val WIFI_CONFIG_ENABLE_DISABLE_DESCRIPTOR = UUID.fromString("c375eac0-d26c-40e6-acac-e2e73c698aa6")

    ////////////////////////////////////////////////////USER CONFIG////////////////////////////////////////////////////

    // read: not supported
    // write: not supported
    val USER_CONFIG_CHARACTERISTIC = UUID.fromString("e6ae1c26-6584-4b20-a97b-31b246c0dd2d")

    // read: on read will return activated = 1 if the device is activated otherwise 0
    // write: on write will try to activate device with specified activation code
    // format: "Activation code", example "3284092384902384"
    val USER_CONFIG_ACTIVATION_DESCRIPTOR = UUID.fromString("cc26e8b7-27e2-40bf-8a08-b361d65838c1")
}