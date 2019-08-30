package com.example.wellbehelperapp.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wellbehelperapp.OnItemClickListener
import com.example.wellbehelperapp.R
import com.example.wellbehelperapp.activities.MainActivity
import com.example.wellbehelperapp.adapters.NetworkAdapters
import com.example.wellbehelperapp.app
import com.example.wellbehelperapp.models.WifiNetwork
import com.example.wellbehelperapp.showInputDialog
import kotlinx.android.synthetic.main.fragment_watch_config.*

class WatchConfigFragment : BaseTabFragment() {

    override val tabTitle by lazy {
        app.getString(R.string.watch_config)
    }

    override val layoutResId = R.layout.fragment_watch_config

    private val activityCallbacks by lazy { requireActivity() as MainActivity }

    private val networksAdapter by lazy {
        NetworkAdapters(
            object : OnItemClickListener<WifiNetwork> {
                override fun onItemClick(data: WifiNetwork) {
                    requireContext().showInputDialog(
                        "Enter Password"
                    ) { pass ->
                        activityCallbacks.sendWifiConfig(data.bssid, "R7tph8cW25")
                    }
                }
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wifiListRv.adapter = networksAdapter
        wifiListRv.layoutManager = LinearLayoutManager(requireContext())

        discoverWifiNetBtn.setOnClickListener {
            activityCallbacks.getWifiList()
        }

        connectionStateBtn.setOnClickListener {
            activityCallbacks.getWifiConnectionState()
        }

        wifiEnabledDisableBtn.setOnClickListener {
            var isEnabled = false
            val text = wifiEnabledDisableStateTv.text.toString()
            if (text.isNotBlank()) {
                isEnabled = text.toBoolean()
            }
            activityCallbacks.setWifiEnabled(!isEnabled)
        }

        wifiEnabledDisableStateBtn.setOnClickListener {
            activityCallbacks.getWifiState()
        }

        activationStateBtn.setOnClickListener {
            activityCallbacks.getActivationState()
        }

        activationBtn.setOnClickListener {
            activityCallbacks.activateUser("19AFC2E4-AE2F-4563-85BC-C2E2F5C45BD5")
        }
    }

    fun setWifiOnOffState(result: String) {
        wifiEnabledDisableStateTv.text = result
    }

    fun setNetworks(networks: MutableList<WifiNetwork>) {
        networksAdapter.setItems(networks)
    }

    fun setWifiConnectionState(data: String) {
        connectionStateTv.text = data
    }

    fun setActivationState(data: String) {
        activationStateTv.text = data
    }
}