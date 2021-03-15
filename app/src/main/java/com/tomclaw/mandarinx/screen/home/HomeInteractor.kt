package com.tomclaw.mandarinx.screen.home

import com.tomclaw.mandarinx.core.Dialogs
import com.tomclaw.mandarinx.dto.Dialog
import com.tomclaw.mandarinx.util.SchedulersFactory
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