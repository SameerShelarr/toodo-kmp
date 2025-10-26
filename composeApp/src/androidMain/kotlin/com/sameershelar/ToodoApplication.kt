package com.sameershelar

import android.app.Application
import com.sameershelar.toodo.di.initKoin
import org.koin.android.ext.koin.androidContext

class ToodoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@ToodoApplication)
        }
    }
}