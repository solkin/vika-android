package com.tomclaw.mandarinx.core

import android.database.sqlite.SQLiteDatabase
import com.tomclaw.mandarinx.dto.Dialog
import com.tomclaw.mandarinx.dto.DialogType
import io.reactivex.Observable

interface Dialogs {

    fun list(): Observable<List<Dialog>>

    fun add(dialog: Dialog)

    fun delete(chatId: Int)

}

class DialogsImpl(val db: SQLiteDatabase) : Dialogs {

    override fun list(): Observable<List<Dialog>> {
        val list = ArrayList<Dialog>()
        for (c in 0..10) {
            list.add(
                Dialog(
                    c,
                    System.currentTimeMillis() + c,
                    DialogType.PRIVATE,
                    "Dialog $c",
                    null,
                    c,
                    null,
                    emptyList()
                )
            )
        }
        return Observable.just(list)
    }

    override fun add(dialog: Dialog) {
        TODO("Not yet implemented")
    }

    override fun delete(chatId: Int) {
        TODO("Not yet implemented")
    }

}