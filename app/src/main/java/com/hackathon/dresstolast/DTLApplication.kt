package com.hackathon.dresstolast

import android.app.Application
import com.hackathon.dresstolast.di.appModule
import com.hackathon.dresstolast.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DTLApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DTLApplication)
            modules(listOf(appModule, viewModelModule))
        }
    }
}