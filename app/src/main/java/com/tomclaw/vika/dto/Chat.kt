package com.tomclaw.vika.dto

import android.os.Parcel
import android.os.Parcelable

class Chat(
    val chatId: Int,
    val updateTime: Long,
    val type: ChatType,
    val title: String,
    var icon: String?,
    var unreadCount: Int,
    var lastMsg: Message?,
    var participants: List<Participant>
) : Parcelable {

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(chatId)
        writeLong(updateTime)
        writeInt(type.value)
        writeString(title)
        writeString(icon)
        writeInt(unreadCount)
        writeParcelable(lastMsg, flags)
        writeTypedList(participants)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Chat> {
        override fun createFromParcel(parcel: Parcel): Chat {
            val chatId = parcel.readInt()
            val updateTime = parcel.readLong()
            val type = ChatType.fromInt(parcel.readInt())
            val title = parcel.readString().orEmpty()
            val icon = parcel.readString()
            val unreadCount = parcel.readInt()
            val lastMsg = parcel.readParcelable<Message>(Message::class.java.classLoader)
            val participants = ArrayList<Participant>().apply {
                parcel.readTypedList(this, Participant.CREATOR)
            }
            return Chat(chatId, updateTime, type, title, icon, unreadCount, lastMsg, participants)
        }

        override fun newArray(size: Int): Array<Chat?> {
            return arrayOfNulls(size)
        }
    }

}

enum class ChatType(val value: Int) {
    PRIVATE(0),
    GROUP(1);

    companion object {
        fun fromInt(value: Int) = values().first { it.value == value }
    }
}
