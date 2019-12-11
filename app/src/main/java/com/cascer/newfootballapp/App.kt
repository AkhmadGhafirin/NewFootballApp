package com.cascer.newfootballapp

import android.app.Application
import com.cascer.newfootballapp.di.appModule
import com.cascer.newfootballapp.di.networkModule
import com.cascer.newfootballapp.di.viewModelModule
import org.koin.android.ext.android.startKoin
import org.koin.android.logger.AndroidLogger

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(
            this,
            listOf(appModule, networkModule, viewModelModule),
            logger = AndroidLogger(showDebug = true)
        )
    }
}