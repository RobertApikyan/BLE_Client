package com.robertapikyan.bleConnection.utils

import java.util.concurrent.CopyOnWriteArraySet

abstract class Notifier<C> {

    private val listeners = CopyOnWriteArraySet<C>()

    fun addListener(listener:C) = listeners.add(listener)

    fun removeListener(listener: C) = listeners.remove(listener)

    fun removeAll() = listeners.clear()

    fun clearAndAdd(listener:C) = with(listeners){
        clear()
        add(listener)
    }

    protected fun getListeners() = listeners
}