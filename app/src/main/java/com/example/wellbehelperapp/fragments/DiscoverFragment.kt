package com.example.wellbehelperapp.fragments

import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import android.bluetooth.BluetoothGattService
import android.bluetooth.le.ScanResult
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wellbehelperapp.OnItemClickListener
import com.example.wellbehelperapp.R
import com.example.wellbehelperapp.activities.MainActivity
import com.example.wellbehelperapp.adapters.CharsAdapter
import com.example.wellbehelperapp.adapters.DescriptorsAdapter
import com.example.wellbehelperapp.adapters.DevicesAdapter
import com.example.wellbehelperapp.adapters.ServicesAdapter
import com.example.wellbehelperapp.app
import com.example.wellbehelperapp.toast
import kotlinx.android.synthetic.main.fragment_dicover.*

class DiscoverFragment : BaseTabFragment() {

    override val tabTitle by lazy {
        app.getString(R.string.discover)
    }

    override val layoutResId = R.layout.fragment_dicover

    private val activityCallbacks by lazy { requireActivity() as MainActivity }

    private val onDeviceSelectListener = object :
        OnItemClickListener<ScanResult> {
        override fun onItemClick(data: ScanResult) {
            activityCallbacks.connectGatt(data)
        }
    }

    private val onServiceSelectedListener = object :
        OnItemClickListener<BluetoothGattService> {
        override fun onItemClick(data: BluetoothGattService) {
            setCharacteristics(data.characteristics)
        }
    }

    private val onCharSelectedListener = object :
        OnItemClickListener<BluetoothGattCharacteristic> {
        override fun onItemClick(data: BluetoothGattCharacteristic) {
            setDescriptors(data.descriptors)
        }
    }

    private val onDescriptorListener = object :
        OnItemClickListener<BluetoothGattDescriptor> {
        override fun onItemClick(data: BluetoothGattDescriptor) {

        }
    }

    private val devicesAdapter by lazy {
        DevicesAdapter(onDeviceSelectListener)
    }

    private val servicesAdapter by lazy {
        ServicesAdapter(onServiceSelectedListener)
    }

    private val charAdapter by lazy {
        CharsAdapter(onCharSelectedListener)
    }

    private val descAdapter by lazy {
        DescriptorsAdapter(onDescriptorListener)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // config rv
        devicesRv.layoutManager = LinearLayoutManager(requireContext())
        devicesRv.adapter = devicesAdapter

        // services rv
        servicesRv.layoutManager = LinearLayoutManager(requireContext())
        servicesRv.adapter = servicesAdapter

        // characteristics rv
        charRv.layoutManager = LinearLayoutManager(requireContext())
        charRv.adapter = charAdapter

        // descriptors rv
        descRv.layoutManager = LinearLayoutManager(requireContext())
        descRv.adapter = descAdapter

        startScanBtn.setOnClickListener {
            activityCallbacks.startScan()
        }

        stopScanBtn.setOnClickListener {
            activityCallbacks.stopScan()
            clearAllData()
        }
    }

    fun setCharacteristics(characteristics: MutableList<BluetoothGattCharacteristic>) {
        charAdapter.setItems(characteristics)
    }

    fun setDescriptors(descriptors: MutableList<BluetoothGattDescriptor>) {
        descAdapter.setItems(descriptors)
    }

    fun setServices(services: MutableList<BluetoothGattService>) {
        servicesAdapter.setItems(services)
    }

    fun setDevices(devices: MutableList<ScanResult>) {
        devicesAdapter.setItems(devices)
    }

    fun clearAllData() {
        charAdapter.clear()
        descAdapter.clear()
        servicesAdapter.clear()
        devicesAdapter.clear()
    }
}