package com.tomclaw.vika.screen.chat.di

import com.tomclaw.vika.screen.chat.ChatActivity
import com.tomclaw.vika.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [ChatModule::class])
interface ChatComponent {

    fun inject(activity: ChatActivity)

}
