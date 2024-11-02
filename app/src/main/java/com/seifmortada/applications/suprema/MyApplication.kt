package com.seifmortada.applications.suprema

import android.app.Application
import android.content.Context
import com.seifmortada.applications.suprema.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }

    companion object {
        private var instance: MyApplication? = null

        fun getContext(): Context {
            return instance!!.applicationContext
        }
    }
}