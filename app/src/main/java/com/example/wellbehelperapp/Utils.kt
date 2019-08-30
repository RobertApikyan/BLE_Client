package com.example.wellbehelperapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import java.util.*

fun convertFromInteger(i: Int): UUID {
    val MSB = 0x0000000000001000L
    val LSB = -0x7fffff7fa064cb05L
    val value = (i and -0x1).toLong()
    return UUID(MSB or (value shl 32), LSB)
}

interface OnItemClickListener<D> {
    fun onItemClick(data: D)
}

fun Context.showInputDialog(
    title: String,
    onTextSubmit: (String) -> Unit
) {
    val builder = AlertDialog.Builder(this)
    builder.setTitle(title)

    val input = EditText(this)

    input.layoutParams = ViewGroup.MarginLayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    ).apply {
        marginStart = 48
        marginEnd = 48
    }

    input.setPadding(48,input.paddingTop,48,input.paddingBottom)

    builder.setView(input)

    builder.setPositiveButton("Send") { dialog, which ->
        dialog.dismiss()
        onTextSubmit(input.text.toString())
    }
    builder.setNegativeButton("Cancel") { dialog, which ->
        dialog.dismiss()
    }
    builder.show()
}

fun toast(text:String){
    uiHandler.post {
        Toast.makeText(app,text,Toast.LENGTH_SHORT).show()
    }
}
