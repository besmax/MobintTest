package bes.max.mobinttest.core

import android.app.Application
import bes.max.mobinttest.di.dataModule
import bes.max.mobinttest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(dataModule, viewModelModule)
        }
    }
}