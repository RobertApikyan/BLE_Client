package com.example.wellbehelperapp.adapters

import android.view.View
import com.example.wellbehelperapp.OnItemClickListener
import com.example.wellbehelperapp.R
import com.example.wellbehelperapp.models.WifiNetwork
import kotlinx.android.synthetic.main.item_row.*

class NetworkAdapters(
    private val onItemClickListener: OnItemClickListener<WifiNetwork>
) : AppAdapter<WifiNetwork, NetworkAdapters.ViewHolder>() {

    override fun getLayoutResId(viewType: Int) = R.layout.item_row

    override fun getViewHolder(itemView: View, viewType: Int) =
        ViewHolder(itemView, onItemClickListener)

    class ViewHolder(itemView: View, onItemClickListener: OnItemClickListener<WifiNetwork>) :
        AppViewHolder<WifiNetwork>(itemView) {

        init {
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(data)
            }
        }

        override fun onBind(data: WifiNetwork) {
            super.onBind(data)
            deviceNameTv.text = if (data.ssid.isNotBlank()){
                data.ssid
            }else {
                data.bssid
            }
        }
    }
}