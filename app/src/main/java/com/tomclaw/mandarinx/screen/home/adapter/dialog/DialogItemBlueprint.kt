package com.tomclaw.mandarinx.screen.home.adapter.dialog

import com.avito.konveyor.blueprint.Item
import com.avito.konveyor.blueprint.ItemBlueprint
import com.avito.konveyor.blueprint.ItemPresenter
import com.avito.konveyor.blueprint.ViewHolderBuilder
import com.tomclaw.mandarinx.R

class DialogItemBlueprint(override val presenter: ItemPresenter<DialogItemView, DialogItem>) :
    ItemBlueprint<DialogItemView, DialogItem> {

    override val viewHolderProvider = ViewHolderBuilder.ViewHolderProvider(
        layoutId = R.layout.dialog_item,
        creator = { _, view -> DialogItemViewHolder(view) }
    )

    override fun isRelevantItem(item: Item) = item is DialogItem

}
