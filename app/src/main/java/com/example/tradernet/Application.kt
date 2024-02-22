package com.example.tradernet

import android.app.Application
import com.example.tradernet.di.appModule
import com.example.tradernet.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(appModule, dataModule)
        }
    }
}