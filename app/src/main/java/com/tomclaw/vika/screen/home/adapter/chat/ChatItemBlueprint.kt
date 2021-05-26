package com.tomclaw.vika.screen.home.adapter.chat

import com.avito.konveyor.blueprint.Item
import com.avito.konveyor.blueprint.ItemBlueprint
import com.avito.konveyor.blueprint.ItemPresenter
import com.avito.konveyor.blueprint.ViewHolderBuilder
import com.tomclaw.vika.R

class ChatItemBlueprint(override val presenter: ItemPresenter<ChatItemView, ChatItem>) :
    ItemBlueprint<ChatItemView, ChatItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.chat_item,
        creator = { _, view -> ChatItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is ChatItem

}
