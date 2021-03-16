package com.tomclaw.mandarinx.screen.home.adapter.dialog

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.avito.konveyor.adapter.BaseViewHolder
import com.avito.konveyor.blueprint.ItemView
import com.tomclaw.mandarinx.R
import com.tomclaw.mandarinx.core.GlideApp
import com.tomclaw.mandarinx.util.bind

interface DialogItemView : ItemView {

    fun setIcon(url: String?)

    fun setTitle(title: String)

    fun setSubtitle(subtitle: String?)

    fun setOnClickListener(listener: (() -> Unit)?)

}

class DialogItemViewHolder(view: View) : BaseViewHolder(view), DialogItemView {

    private val icon: ImageView = view.findViewById(R.id.icon)
    private val title: TextView = view.findViewById(R.id.title)
    private val subtitle: TextView = view.findViewById(R.id.subtitle)

    private var listener: (() -> Unit)? = null

    init {
        view.setOnClickListener { listener?.invoke() }
    }

    override fun setIcon(url: String?) {
        GlideApp.with(icon)
            .load(url)
            .placeholder(R.drawable.ic_avatar_placeholder)
            .circleCrop()
            .into(icon)
    }

    override fun setTitle(title: String) {
        this.title.bind(title)
    }

    override fun setSubtitle(subtitle: String?) {
        this.subtitle.bind(subtitle)
    }

    override fun setOnClickListener(listener: (() -> Unit)?) {
        this.listener = listener
    }

    override fun onUnbind() {
        this.listener = null
    }

}
