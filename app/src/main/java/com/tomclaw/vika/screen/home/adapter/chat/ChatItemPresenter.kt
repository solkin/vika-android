package com.tomclaw.vika.screen.home.adapter.chat

import com.avito.konveyor.blueprint.ItemPresenter
import com.tomclaw.vika.screen.home.adapter.ItemClickListener

class ChatItemPresenter(
    private val listener: ItemClickListener
) : ItemPresenter<ChatItemView, ChatItem> {

    override fun bindView(view: ChatItemView, item: ChatItem, position: Int) {
        view.setIcon(item.icon)
        view.setTitle(item.title)
        view.setSubtitle(item.subtitle)
        view.setOnClickListener { listener.onItemClick(item) }
    }

}
