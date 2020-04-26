package com.scyter.lifetech

import android.app.Application


class LifetechApp : Application() {

    companion object {
        lateinit var instance: LifetechApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}