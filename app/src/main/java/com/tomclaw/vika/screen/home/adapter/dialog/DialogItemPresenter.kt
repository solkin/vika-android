package com.tomclaw.vika.screen.home.adapter.dialog

import com.avito.konveyor.blueprint.ItemPresenter
import com.tomclaw.vika.screen.home.adapter.ItemClickListener

class DialogItemPresenter(
    private val listener: ItemClickListener
) : ItemPresenter<DialogItemView, DialogItem> {

    override fun bindView(view: DialogItemView, item: DialogItem, position: Int) {
        view.setIcon(item.icon)
        view.setTitle(item.title)
        view.setSubtitle(item.subtitle)
        view.setOnClickListener { listener.onItemClick(item) }
    }

}
