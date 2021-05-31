package com.tomclaw.vika.screen.chat

import com.tomclaw.vika.util.SchedulersFactory

interface ChatInteractor {
}

class ChatInteractorImpl(
    private val schedulers: SchedulersFactory
) : ChatInteractor {
}
