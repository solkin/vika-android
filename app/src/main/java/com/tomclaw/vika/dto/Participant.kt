package com.tomclaw.vika.dto

import android.os.Parcel
import android.os.Parcelable

class Participant(
    val userId: Int,
    val readMsgId: Int,
    val chatRole: ChatRole
) : Parcelable {

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(userId)
        writeInt(readMsgId)
        writeInt(chatRole.value)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Participant> {
        override fun createFromParcel(parcel: Parcel): Participant {
            val userId = parcel.readInt()
            val readMsgId = parcel.readInt()
            val chatRole = ChatRole.fromInt(parcel.readInt())
            return Participant(userId, readMsgId, chatRole)
        }

        override fun newArray(size: Int): Array<Participant?> {
            return arrayOfNulls(size)
        }
    }
}

enum class ChatRole(val value: Int) {
    BANNED(-2),
    EXCLUDED(-2),
    READ_ONLY(-1),
    NORMAL(0),
    ADMIN(1);

    companion object {
        fun fromInt(value: Int) = values().first { it.value == value }
    }
}
