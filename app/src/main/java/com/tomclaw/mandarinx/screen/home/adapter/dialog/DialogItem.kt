package com.tomclaw.mandarinx.screen.home.adapter.dialog

import android.os.Parcel
import android.os.Parcelable
import com.avito.konveyor.blueprint.Item

class DialogItem(
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

    companion object CREATOR : Parcelable.Creator<DialogItem> {
        override fun createFromParcel(parcel: Parcel): DialogItem {
            val id = parcel.readLong()
            val icon = parcel.readString()
            val title = parcel.readString().orEmpty()
            val subtitle = parcel.readString()
            return DialogItem(id, icon, title, subtitle)
        }

        override fun newArray(size: Int): Array<DialogItem?> {
            return arrayOfNulls(size)
        }
    }

}
