package com.tomclaw.vika.screen.home

import com.tomclaw.vika.core.Chats
import com.tomclaw.vika.dto.Chat
import com.tomclaw.vika.util.SchedulersFactory
import io.reactivex.Observable

interface HomeInteractor {

    fun listChats(): Observable<List<Chat>>

}

class HomeInteractorImpl(
    private val chats: Chats,
    private val schedulers: SchedulersFactory
) : HomeInteractor {

    override fun listChats(): Observable<List<Chat>> {
        return chats.list()
            .subscribeOn(schedulers.io())
    }

}