package com.example.caasworkshop

import android.app.Application
import com.example.caasworkshop.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        //Here, we start Koin, providing it with a Logger(not relevant, but needed), a context (this current app), and the modules to create, which we defined in AppModule
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApp)
            modules(appModule)
        }
    }
}