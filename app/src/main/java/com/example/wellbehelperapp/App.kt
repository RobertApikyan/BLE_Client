package com.example.wellbehelperapp

import android.app.Application
import android.os.Handler

lateinit var app:App

lateinit var uiHandler:Handler

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        app = this
        uiHandler = Handler()
    }
}