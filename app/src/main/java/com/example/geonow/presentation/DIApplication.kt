package com.example.geonow.presentation

import android.app.Application
import com.example.geonow.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DIApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DIApplication)
            modules(AppModule)
        }
    }
}
