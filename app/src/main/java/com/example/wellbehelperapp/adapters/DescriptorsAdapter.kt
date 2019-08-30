package com.example.wellbehelperapp.adapters

import android.bluetooth.BluetoothGattDescriptor
import android.view.View
import com.example.wellbehelperapp.OnItemClickListener
import com.example.wellbehelperapp.R
import kotlinx.android.synthetic.main.item_row.*

class DescriptorsAdapter(
    private val onItemClickListener: OnItemClickListener<BluetoothGattDescriptor>
) : AppAdapter<BluetoothGattDescriptor, DescriptorsAdapter.ViewHolder>() {

    override fun getLayoutResId(viewType: Int) = R.layout.item_row

    override fun getViewHolder(itemView: View, viewType: Int) =
        ViewHolder(
            itemView,
            onItemClickListener
        )

    class ViewHolder(itemView: View, onItemClickListener: OnItemClickListener<BluetoothGattDescriptor>) :
        AppViewHolder<BluetoothGattDescriptor>(itemView) {

        init {
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(data)
            }
        }

        override fun onBind(data: BluetoothGattDescriptor) {
            super.onBind(data)
            deviceNameTv.text = data.uuid.toString()
        }
    }
}