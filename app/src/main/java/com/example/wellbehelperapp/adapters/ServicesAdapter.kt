package com.example.wellbehelperapp.adapters

import android.bluetooth.BluetoothGattService
import android.view.View
import com.example.wellbehelperapp.OnItemClickListener
import com.example.wellbehelperapp.R
import kotlinx.android.synthetic.main.item_row.*

class ServicesAdapter(
    private val onItemClickListener: OnItemClickListener<BluetoothGattService>
) : AppAdapter<BluetoothGattService, ServicesAdapter.ViewHolder>() {

    override fun getLayoutResId(viewType: Int) = R.layout.item_row

    override fun getViewHolder(itemView: View, viewType: Int) =
        ViewHolder(
            itemView,
            onItemClickListener
        )

    class ViewHolder(itemView: View, onItemClickListener: OnItemClickListener<BluetoothGattService>) :
        AppViewHolder<BluetoothGattService>(itemView) {

        init {
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(data)
            }
        }

        override fun onBind(data: BluetoothGattService) {
            super.onBind(data)
            deviceNameTv.text = data.uuid.toString()
        }
    }
}