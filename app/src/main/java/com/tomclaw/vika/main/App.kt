package com.tomclaw.vika.main

import android.app.Application
import com.tomclaw.vika.di.AppComponent
import com.tomclaw.vika.di.AppModule
import com.tomclaw.vika.di.DaggerAppComponent

class App : Application() {

    lateinit var component: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        component = buildComponent()
    }

    private fun buildComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}

fun Application.getComponent(): AppComponent {
    return (this as App).component
}