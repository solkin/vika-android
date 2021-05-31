package com.tomclaw.vika.screen.chat.di

import android.content.Context
import android.os.Bundle
import com.tomclaw.vika.core.Chats
import com.tomclaw.vika.screen.chat.ChatInteractor
import com.tomclaw.vika.screen.chat.ChatInteractorImpl
import com.tomclaw.vika.screen.chat.ChatPresenter
import com.tomclaw.vika.screen.chat.ChatPresenterImpl
import com.tomclaw.vika.util.PerActivity
import com.tomclaw.vika.util.SchedulersFactory
import dagger.Module
import dagger.Provides

@Module
class ChatModule(
    private val context: Context,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        interactor: ChatInteractor,
        schedulers: SchedulersFactory
    ): ChatPresenter = ChatPresenterImpl(
        interactor,
        schedulers,
        state
    )

    @Provides
    @PerActivity
    internal fun provideInteractor(
        chats: Chats,
        schedulers: SchedulersFactory
    ): ChatInteractor = ChatInteractorImpl(schedulers)

}