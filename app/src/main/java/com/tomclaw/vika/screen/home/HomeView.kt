package com.tomclaw.vika.screen.home

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avito.konveyor.adapter.SimpleRecyclerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jakewharton.rxrelay2.PublishRelay
import com.tomclaw.vika.R
import io.reactivex.Observable

interface HomeView {

    fun showProgress()

    fun showContent()

    fun contentUpdated()

    fun navigationClicks(): Observable<Unit>

    fun openChatClicks(): Observable<Unit>

}

class HomeViewImpl(
        private val view: View,
        private val adapter: SimpleRecyclerAdapter
) : HomeView {

    private val context = view.context
    private val toolbar: Toolbar = view.findViewById(R.id.toolbar)
    private val openChatButton: FloatingActionButton = view.findViewById(R.id.open_chat)
    private val recycler: RecyclerView = view.findViewById(R.id.recycler)

    private val navigationRelay = PublishRelay.create<Unit>()
    private val openChatRelay = PublishRelay.create<Unit>()

    init {
        toolbar.setTitle(R.string.app_name)

        val orientation = RecyclerView.VERTICAL
        val layoutManager = LinearLayoutManager(view.context, orientation, false)
        adapter.setHasStableIds(true)
        recycler.adapter = adapter
        recycler.layoutManager = layoutManager
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.itemAnimator?.changeDuration = DURATION_MEDIUM

        openChatButton.setOnClickListener {
            openChatRelay.accept(Unit)
        }
    }

    override fun showProgress() {

    }

    override fun showContent() {

    }

    override fun contentUpdated() {
        adapter.notifyDataSetChanged()
    }

    override fun navigationClicks(): Observable<Unit> = navigationRelay

    override fun openChatClicks(): Observable<Unit> {
        return openChatRelay
    }

}

private const val DURATION_MEDIUM = 300L
