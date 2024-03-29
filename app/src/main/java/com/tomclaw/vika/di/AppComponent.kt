package com.tomclaw.vika.di

import com.tomclaw.vika.screen.chat.di.ChatComponent
import com.tomclaw.vika.screen.chat.di.ChatModule
import com.tomclaw.vika.screen.home.di.HomeComponent
import com.tomclaw.vika.screen.home.di.HomeModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun homeComponent(module: HomeModule): HomeComponent

    fun chatComponent(module: ChatModule): ChatComponent

}
