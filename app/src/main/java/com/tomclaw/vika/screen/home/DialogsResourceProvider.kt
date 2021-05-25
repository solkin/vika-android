package com.tomclaw.vika.screen.home

import android.annotation.SuppressLint
import android.content.res.Resources
import com.tomclaw.vika.R
import java.text.SimpleDateFormat
import java.util.Locale

interface DialogsResourceProvider {

    fun formatDate(time: Long): String

    fun noMessages(): String

}

class DialogsResourceProviderImpl(val resources: Resources) : DialogsResourceProvider {

    @SuppressLint("ConstantLocale")
    private val simpleDateFormat = SimpleDateFormat("HH:mm dd.MM.yy", Locale.getDefault())

    override fun formatDate(time: Long): String {
        val date = simpleDateFormat.format(time)
        return resources.getString(R.string.last_seen, date)
    }

    override fun noMessages(): String {
        return resources.getString(R.string.no_messages)
    }

}