package com.tomclaw.vika.screen.home

import com.tomclaw.vika.core.Dialogs
import com.tomclaw.vika.dto.Dialog
import com.tomclaw.vika.util.SchedulersFactory
import io.reactivex.Observable

interface HomeInteractor {

    fun listDialogs(): Observable<List<Dialog>>

}

class HomeInteractorImpl(
    private val dialogs: Dialogs,
    private val schedulers: SchedulersFactory
) : HomeInteractor {

    override fun listDialogs(): Observable<List<Dialog>> {
        return dialogs.list()
            .subscribeOn(schedulers.io())
    }

}