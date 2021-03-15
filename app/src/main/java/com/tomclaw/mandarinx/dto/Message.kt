package com.tomclaw.mandarinx.dto

import android.os.Parcel
import android.os.Parcelable

class Message(
    val chatId: Int,
    val msgId: Int,
    val prevMsgId: Int,
    val senderId: Int,
    val time: Long,
    val text: String
) : Parcelable {

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(chatId)
        writeInt(msgId)
        writeInt(prevMsgId)
        writeInt(senderId)
        writeLong(time)
        writeString(text)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Message> {
        override fun createFromParcel(parcel: Parcel): Message {
            val chatId = parcel.readInt()
            val msgId = parcel.readInt()
            val prevMsgId = parcel.readInt()
            val senderId = parcel.readInt()
            val time = parcel.readLong()
            val text = parcel.readString().orEmpty()
            return Message(chatId, msgId, prevMsgId, senderId, time, text)
        }

        override fun newArray(size: Int): Array<Message?> {
            return arrayOfNulls(size)
        }
    }
}
