package com.tomclaw.vika.screen.home

import com.avito.konveyor.blueprint.Item
import com.tomclaw.vika.dto.Dialog
import com.tomclaw.vika.screen.home.adapter.dialog.DialogItem

interface DialogConverter {

    fun convert(dialog: Dialog): Item

}

class DialogConverterImpl(
    private val resourceProvider: DialogsResourceProvider
) : DialogConverter {

    override fun convert(dialog: Dialog): Item {
        return DialogItem(
            id = dialog.chatId.toLong(),
            icon = dialog.icon,
            title = dialog.title,
            subtitle = dialog.lastMsg
                ?.let { resourceProvider.formatDate(it.time) }
                ?: resourceProvider.noMessages()
        )
    }

}