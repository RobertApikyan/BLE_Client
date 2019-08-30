package com.example.wellbehelperapp.adapters

import android.bluetooth.BluetoothGattCharacteristic
import android.view.View
import com.example.wellbehelperapp.OnItemClickListener
import com.example.wellbehelperapp.R
import kotlinx.android.synthetic.main.item_row.*

class CharsAdapter(
    private val onItemClickListener: OnItemClickListener<BluetoothGattCharacteristic>
) : AppAdapter<BluetoothGattCharacteristic, CharsAdapter.ViewHolder>() {

    override fun getLayoutResId(viewType: Int) = R.layout.item_row

    override fun getViewHolder(itemView: View, viewType: Int) =
        ViewHolder(itemView, onItemClickListener)

    class ViewHolder(itemView: View, onItemClickListener: OnItemClickListener<BluetoothGattCharacteristic>) :
        AppViewHolder<BluetoothGattCharacteristic>(itemView) {

        init {
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(data)
            }
        }

        override fun onBind(data: BluetoothGattCharacteristic) {
            super.onBind(data)
            deviceNameTv.text = data.uuid.toString()
        }
    }
}