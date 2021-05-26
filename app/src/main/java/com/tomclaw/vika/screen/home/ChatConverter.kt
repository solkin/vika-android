package com.tomclaw.vika.screen.home

import com.avito.konveyor.blueprint.Item
import com.tomclaw.vika.dto.Chat
import com.tomclaw.vika.screen.home.adapter.chat.ChatItem

interface ChatConverter {

    fun convert(chat: Chat): Item

}

class ChatConverterImpl(
    private val resourceProvider: ChatsResourceProvider
) : ChatConverter {

    override fun convert(chat: Chat): Item {
        return ChatItem(
            id = chat.chatId.toLong(),
            icon = chat.icon,
            title = chat.title,
            subtitle = chat.lastMsg
                ?.let { resourceProvider.formatDate(it.time) }
                ?: resourceProvider.noMessages()
        )
    }

}