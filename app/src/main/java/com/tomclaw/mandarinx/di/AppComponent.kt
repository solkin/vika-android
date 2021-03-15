package com.tomclaw.mandarinx.di

import com.tomclaw.mandarinx.screen.home.di.HomeComponent
import com.tomclaw.mandarinx.screen.home.di.HomeModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun homeComponent(module: HomeModule): HomeComponent

}
