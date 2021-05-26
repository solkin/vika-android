package com.tomclaw.vika.screen.home

import android.os.Bundle
import android.util.LongSparseArray
import com.avito.konveyor.adapter.AdapterPresenter
import com.avito.konveyor.blueprint.Item
import com.avito.konveyor.data_source.ListDataSource
import com.tomclaw.vika.dto.Chat
import com.tomclaw.vika.screen.home.adapter.ItemClickListener
import com.tomclaw.vika.util.SchedulersFactory
import dagger.Lazy
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign

interface HomePresenter : ItemClickListener {

    fun attachView(view: HomeView)

    fun detachView()

    fun attachRouter(router: HomeRouter)

    fun detachRouter()

    fun saveState(): Bundle

    fun onBackPressed()

    fun onUpdate()

    interface HomeRouter {

        fun leaveScreen()

    }

}

class HomePresenterImpl(
    private val interactor: HomeInteractor,
    private val adapterPresenter: Lazy<AdapterPresenter>,
    private val chatConverter: ChatConverter,
    private val schedulers: SchedulersFactory,
    state: Bundle?
) : HomePresenter {

    private var view: HomeView? = null
    private var router: HomePresenter.HomeRouter? = null

    private val subscriptions = CompositeDisposable()

    private val chatIds = LongSparseArray<Chat>()

    override fun attachView(view: HomeView) {
        this.view = view

        subscriptions += view.navigationClicks().subscribe {
            onBackPressed()
        }

        loadChats()
    }

    override fun detachView() {
        subscriptions.clear()
        this.view = null
    }

    override fun attachRouter(router: HomePresenter.HomeRouter) {
        this.router = router
    }

    override fun detachRouter() {
        this.router = null
    }

    override fun saveState() = Bundle().apply {
    }

    private fun loadChats() {
        subscriptions += interactor.listChats()
            .observeOn(schedulers.mainThread())
            .doOnSubscribe { view?.showProgress() }
            .doAfterTerminate { view?.showContent() }
            .subscribe(
                { onLoaded(it) },
                { onError(it) }
            )
    }

    private fun onLoaded(chats: List<Chat>) {
        val items = chats.asSequence()
            .sortedBy { it.lastMsg?.time ?: 0 }
            .map {
                val item = chatConverter.convert(it)
                chatIds.put(item.id, it)
                item
            }
            .toList()
        val dataSource = ListDataSource(items)
        adapterPresenter.get().onDataSourceChanged(dataSource)
        view?.contentUpdated()
    }

    private fun onError(it: Throwable) {}

    override fun onBackPressed() {
        router?.leaveScreen()
    }

    override fun onUpdate() {
        view?.contentUpdated()
    }

    override fun onItemClick(item: Item) {
        val chat = chatIds[item.id] ?: return
//        subscriptions += interactor.switchBook(bookId)
//            .observeOn(schedulers.mainThread())
//            .subscribe(
//                { onBookSwitched() },
//                { onError(it) }
//            )
    }

}
