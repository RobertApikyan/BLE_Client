package com.example.wellbehelperapp.adapters

import android.bluetooth.le.ScanResult
import android.view.View
import com.example.wellbehelperapp.OnItemClickListener
import com.example.wellbehelperapp.R
import kotlinx.android.synthetic.main.item_row.*

class DevicesAdapter(
    private val onItemClickListener: OnItemClickListener<ScanResult>
) : AppAdapter<ScanResult, DevicesAdapter.ViewHolder>() {

    override fun getLayoutResId(viewType: Int) = R.layout.item_row

    override fun getViewHolder(itemView: View, viewType: Int) =
        ViewHolder(
            itemView,
            onItemClickListener
        )

    class ViewHolder(itemView: View, onItemClickListener: OnItemClickListener<ScanResult>) :
        AppViewHolder<ScanResult>(itemView) {

        init {
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(data)
            }
        }

        override fun onBind(data: ScanResult) {
            super.onBind(data)
            deviceNameTv.text = data.device.name ?: data.device.address
        }
    }
}