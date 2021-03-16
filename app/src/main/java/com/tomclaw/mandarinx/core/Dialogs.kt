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
        val avatars = listOf(
            "https://molecus.com/files/stat/molecus-userfiles/user1023/460e006957d905daee26abe1d4b71c186ee773b2-220x220-1.jpg",
            "https://molecus.com/files/stat/molecus-userfiles/user973/82df536cfc16dd2832e09f794cc5bc864c76be0c-220x220-1.jpg",
            "https://molecus.com/files/stat/molecus-userfiles/user1022/1662d4148beedabc12518be67d80d5e2c9a9b1ca-220x220-1.jpg",
            "https://molecus.com/files/stat/molecus-userfiles/user1090/9efde2ac6ba7f98524e1554da223f5e9f98a005a-220x220-1.jpg",
            "https://molecus.com/files/stat/molecus-userfiles/user1014/ce0917f6044a09a988d7f07a1befe54973ed4581-220x220-1.jpg",
            "https://molecus.com/files/stat/molecus-userfiles/user951/8853822ea53f4137c1a5a8b2511de497618c90a3-220x220-1.jpg",
            "https://molecus.com/files/stat/molecus-userfiles/user715/006bffd70e7914af16d77ff5e9c8c571a5b8755a-220x220-1.jpg",
            "https://molecus.com/files/stat/molecus-userfiles/user501/5c70423be5212-220x220-1.png",
            "https://molecus.com/files/stat/molecus-userfiles/user453/5c6db0c982ee9-220x220-1.jpeg",
            "https://molecus.com/files/stat/molecus-userfiles/user964/362b55220394104164cc32935d743f0631c2e41f-220x220-1.jpg",
            "https://molecus.com/files/stat/molecus-userfiles/user737/5c6efb61ccdf9-220x220-1.jpg",
        )
        val list = ArrayList<Dialog>()
        for (c in 0..10) {
            list.add(
                Dialog(
                    c,
                    System.currentTimeMillis() + c,
                    DialogType.PRIVATE,
                    "Dialog $c",
                    avatars[c],
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