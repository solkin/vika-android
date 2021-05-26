package com.tomclaw.vika.screen.home.adapter.chat

import android.os.Parcel
import android.os.Parcelable
import com.avito.konveyor.blueprint.Item

class ChatItem(
    override val id: Long,
    val icon: String?,
    val title: String,
    val subtitle: String?
) : Item, Parcelable {

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(icon)
        writeString(title)
        writeString(subtitle)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<ChatItem> {
        override fun createFromParcel(parcel: Parcel): ChatItem {
            val id = parcel.readLong()
            val icon = parcel.readString()
            val title = parcel.readString().orEmpty()
            val subtitle = parcel.readString()
            return ChatItem(id, icon, title, subtitle)
        }

        override fun newArray(size: Int): Array<ChatItem?> {
            return arrayOfNulls(size)
        }
    }

}
