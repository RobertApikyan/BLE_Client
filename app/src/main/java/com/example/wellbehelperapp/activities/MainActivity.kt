package com.example.wellbehelperapp.activities

import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattDescriptor
import android.bluetooth.BluetoothGattService
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.ParcelUuid
import androidx.appcompat.app.AppCompatActivity
import com.example.wellbehelperapp.BleUUIDs
import com.example.wellbehelperapp.R
import com.example.wellbehelperapp.adapters.TabFragmentPagerAdapter
import com.example.wellbehelperapp.fragments.DiscoverFragment
import com.example.wellbehelperapp.fragments.WatchConfigFragment
import com.example.wellbehelperapp.models.WifiNetwork
import com.example.wellbehelperapp.toast
import com.robertapikyan.bleConnection.dataHandlers.client.characteristic.BleClientCharStringDataHandler
import com.robertapikyan.bleConnection.dataHandlers.client.descriptor.BleClientDescStringDataHandler
import com.robertapikyan.bleConnection.gattCallbacks.BluetoothGattCallbackDelegate
import com.robertapikyan.bleConnection.gattCallbacks.BluetoothGattCallbacks
import kotlinx.android.synthetic.main.activity_main.*


// BLE

val POSITIVE = byteArrayOf(1)
val NEGATIVE = byteArrayOf(0)
val NEUTRAL = byteArrayOf(-1)

fun Boolean?.toBleBoolean() = if (this ?: false) {
    POSITIVE
} else
    NEGATIVE

fun ByteArray?.toBleBoolean() = this?.contentEquals(POSITIVE) ?: false

class MainActivity : AppCompatActivity(),
    BluetoothGattCallbacks.OnConnectionStateChangCallback,
    BluetoothGattCallbacks.OnServicesDiscoveredCallback {

    private val discoverFragment by lazy { DiscoverFragment() }

    private val watchConfigFragment by lazy { WatchConfigFragment() }

    private val pagerAdapter by lazy {
        TabFragmentPagerAdapter(
            supportFragmentManager, listOf(
                discoverFragment,
                watchConfigFragment
            )
        )
    }

    private val blManager by lazy {
        getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    }

    private lateinit var gatt: BluetoothGatt

    private val blAdapter by lazy {
        blManager.adapter
    }

    private val scanDevices = mutableMapOf<String, ScanResult>()

    private var watchService: BluetoothGattService? = null

    private val wifiListCharacteristic by lazy {
        watchService!!.getCharacteristic(BleUUIDs.WIFI_CONFIG_AVAILABLE_NETWORKS_CHARACTERISTIC)!!
    }

    private val userConfigCharacteristic by lazy {
        watchService!!.getCharacteristic(BleUUIDs.USER_CONFIG_CHARACTERISTIC)
    }

    private val wifiListDataHandler by lazy {
        object : BleClientCharStringDataHandler() {

            override fun onDataChanged(data: String) {
                super.onDataChanged(data)
                toast("Wifi List = $data")
                if (data.isNotBlank() && data.contains("|")) {
                    val networksData = data.split("|")
                    val networks = mutableListOf<WifiNetwork>()
                    for (netData in networksData) {
                        if (netData.isNotBlank()) {
                            networks.add(WifiNetwork.fromDataString(netData))
                        }
                    }
                    runOnUiThread {
                        watchConfigFragment.setNetworks(networks)
                    }
                } else {
                    toast("No Networks Discovered")
                }
            }

        }
    }

    private val wifiConfigEnableDisableDescriptor by lazy {
        wifiListCharacteristic.getDescriptor(BleUUIDs.WIFI_CONFIG_ENABLE_DISABLE_DESCRIPTOR)
    }

    private val wifiConfigEnableDisableDescriptorDataHandler by lazy {
        object : BleClientDescStringDataHandler(

        ) {
            // set the remote device wifi state On/Off
            fun setWifiEnabled(enabled: Boolean) {
                write(enabled.toBleBoolean())
            }

            override fun onRead(
                gatt: BluetoothGatt?,
                descriptor: BluetoothGattDescriptor?,
                status: Int
            ) {
                super.onRead(gatt, descriptor, status)
                if (status == BluetoothGatt.GATT_SUCCESS) {
                    val isWifiEnabled = descriptor?.value.toBleBoolean()
                    runOnUiThread {
                        watchConfigFragment.setWifiOnOffState(isWifiEnabled.toString())
                    }
                }
            }

            override fun onWrite(
                gatt: BluetoothGatt?,
                descriptor: BluetoothGattDescriptor?,
                status: Int
            ) {
                super.onWrite(gatt, descriptor, status)
                if (status == BluetoothGatt.GATT_SUCCESS) {
                    // get the wifi status
                    read()
                }
            }
        }
    }

    private val wifiSsidPassDescriptor by lazy {
        wifiListCharacteristic.getDescriptor(BleUUIDs.WIFI_CONFIG_SSID_PASS_DESCRIPTOR)
    }

    private val wifiSsidPassDescDataHandler by lazy {
        object : BleClientDescStringDataHandler() {

            fun setSsidPass(ssid: String, pass: String) {
                writeData("$ssid;$pass")
            }

            override fun onRead(
                gatt: BluetoothGatt?,
                descriptor: BluetoothGattDescriptor?,
                status: Int
            ) {
                super.onRead(gatt, descriptor, status)
                if (status == BluetoothGatt.GATT_SUCCESS) {
                    val isConnectedToWifi = descriptor?.value.toBleBoolean()
                    runOnUiThread {
                        watchConfigFragment.setWifiConnectionState(isConnectedToWifi.toString())
                    }
                }
            }

            override fun onWrite(
                gatt: BluetoothGatt?,
                descriptor: BluetoothGattDescriptor?,
                status: Int
            ) {
                super.onWrite(gatt, descriptor, status)
                if (status == BluetoothGatt.GATT_SUCCESS) {
                    // waits for 2 seconds and check the wifi connection state
                    Thread.sleep(2000)
                    read()
                }
            }
        }
    }

    private val userConfigActivationCodeDescriptor by lazy {
        userConfigCharacteristic!!.getDescriptor(BleUUIDs.USER_CONFIG_ACTIVATION_DESCRIPTOR)!!
    }

    private val userConfigActivationCodeDescriptorStringDataHandler by lazy {
        object : BleClientDescStringDataHandler() {

            fun activateUser(activationCode: String) {
                writeData(activationCode)
            }

            override fun onWrite(
                gatt: BluetoothGatt?,
                descriptor: BluetoothGattDescriptor?,
                status: Int
            ) {
                super.onWrite(gatt, descriptor, status)
                if (status == BluetoothGatt.GATT_SUCCESS) {
                    // waits for 2 seconds and check the status
                    Thread.sleep(2000)
                    read()
                }
            }

            override fun onRead(
                gatt: BluetoothGatt?,
                descriptor: BluetoothGattDescriptor?,
                status: Int
            ) {
                super.onRead(gatt, descriptor, status)
                if (status == BluetoothGatt.GATT_SUCCESS) {
                    val isActivated = descriptor?.value.toBleBoolean()
                    runOnUiThread {
                        watchConfigFragment.setActivationState(isActivated.toString())
                    }
                }
            }
        }
    }

    private val gattCallbackDelegate = BluetoothGattCallbackDelegate()

    override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {

        this@MainActivity.gatt = gatt!!

        val services =
            gatt.services.filter { it.uuid == BleUUIDs.WATCH_SERVICE }.toMutableList()

        this@MainActivity.watchService = services.first()

        with(gattCallbackDelegate) {
            wifiListDataHandler.setupWithCharacteristic(wifiListCharacteristic)
            wifiListDataHandler.setupWithGatt(gatt)
            characteristicChangedNotifier.addListener(wifiListDataHandler)
            characteristicWriteNotifier.addListener(wifiListDataHandler)
            characteristicReadNotifier.addListener(wifiListDataHandler)

            wifiSsidPassDescDataHandler.setupWithDescriptor(wifiSsidPassDescriptor)
            wifiSsidPassDescDataHandler.setupWithGatt(gatt)
            descriptorWriteNotifier.addListener(wifiSsidPassDescDataHandler)
            descriptorReadNotifier.addListener(wifiSsidPassDescDataHandler)

            wifiConfigEnableDisableDescriptorDataHandler.setupWithDescriptor(
                wifiConfigEnableDisableDescriptor
            )
            wifiConfigEnableDisableDescriptorDataHandler.setupWithGatt(gatt)
            descriptorWriteNotifier.addListener(wifiConfigEnableDisableDescriptorDataHandler)
            descriptorReadNotifier.addListener(wifiConfigEnableDisableDescriptorDataHandler)

            userConfigActivationCodeDescriptorStringDataHandler.setupWithDescriptor(
                userConfigActivationCodeDescriptor
            )
            userConfigActivationCodeDescriptorStringDataHandler.setupWithGatt(gatt)
            descriptorWriteNotifier.addListener(userConfigActivationCodeDescriptorStringDataHandler)
            descriptorReadNotifier.addListener(userConfigActivationCodeDescriptorStringDataHandler)
        }

        gatt.setCharacteristicNotification(wifiListCharacteristic, true)

        runOnUiThread {
            discoverFragment.setServices(services)
        }
    }

    override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
        this@MainActivity.gatt = gatt!!

        if (newState == BluetoothGatt.STATE_CONNECTED) {
            gatt.discoverServices()
        }
        if (newState == BluetoothGatt.STATE_DISCONNECTED) {
            scanDevices.clear()
            discoverFragment.clearAllData()
        }
    }

    private var blScannerCallback: ScanCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    fun startScan() {
        if (blAdapter != null) {
            if (!blAdapter.isEnabled) {
                val isSetUpSuccessfullyStarted = blAdapter.enable()
                if (!isSetUpSuccessfullyStarted) {
                    toast("Failed to enable bluetooth")
                }
            } else {
                val filters = mutableListOf<ScanFilter>()

                filters.add(
                    ScanFilter.Builder()
                        .setServiceUuid(ParcelUuid(BleUUIDs.WATCH_SERVICE))
                        .build()
                )

                val settings = ScanSettings.Builder()
                    .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                    .build()

                blScannerCallback = createLeScanCallbacks()
                blAdapter.bluetoothLeScanner.startScan(filters, settings, blScannerCallback)
                toast("Start LE Scan")
            }
        } else {
            toast("Device do not have bluetooth adapter")
        }
    }

    fun createLeScanCallbacks() = object : ScanCallback() {

        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)

            if (!scanDevices.contains(result.device.toString())) {
                toast("Device discovered")
                scanDevices[result.device.toString()] = result
                runOnUiThread {
                    discoverFragment.setDevices(scanDevices.values.toMutableList())
                }
            }
        }

        override fun onScanFailed(errorCode: Int) {
            super.onScanFailed(errorCode)
            toast("Scan Failed code= $errorCode")
        }
    }

    fun stopScan() {
        if (blScannerCallback == null) {
            return
        }
        blAdapter.bluetoothLeScanner.stopScan(blScannerCallback)
        blScannerCallback = null
        if (::gatt.isInitialized) {
            gatt.disconnect()
        }
        scanDevices.clear()
        toast("Stop LE Scan")
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun connectGatt(data: ScanResult) {

        with(gattCallbackDelegate) {
            removeAll()
            connectionStateChangeNotifier.addListener(this@MainActivity)
            serviceDiscoveredNotifier.addListener(this@MainActivity)
        }

        gatt = data.device.connectGatt(this@MainActivity, false, gattCallbackDelegate)

        gatt.discoverServices()
    }

    fun getWifiList() {
        wifiListDataHandler.read()
    }

    fun getWifiConnectionState() {
        wifiSsidPassDescDataHandler.read()
    }

    fun sendWifiConfig(bssid: String, pass: String) {
        wifiSsidPassDescDataHandler.setSsidPass(bssid, pass)
    }

    fun setWifiEnabled(enabled: Boolean) {
        wifiConfigEnableDisableDescriptorDataHandler.setWifiEnabled(enabled)
    }

    fun getWifiState() {
        wifiConfigEnableDisableDescriptorDataHandler.read()
    }

    fun getActivationState() {
        userConfigActivationCodeDescriptorStringDataHandler.read()
    }

    fun activateUser(activationCode: String) {
        userConfigActivationCodeDescriptorStringDataHandler.activateUser(activationCode)
    }
}
