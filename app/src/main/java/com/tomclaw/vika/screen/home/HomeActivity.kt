package com.tomclaw.vika.screen.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.avito.konveyor.ItemBinder
import com.avito.konveyor.adapter.AdapterPresenter
import com.avito.konveyor.adapter.SimpleRecyclerAdapter
import com.tomclaw.vika.R
import com.tomclaw.vika.main.getComponent
import com.tomclaw.vika.screen.chat.createChatActivityIntent
import com.tomclaw.vika.screen.home.di.HomeModule
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomePresenter.HomeRouter {

    @Inject
    lateinit var presenter: HomePresenter

    @Inject
    lateinit var adapterPresenter: AdapterPresenter

    @Inject
    lateinit var binder: ItemBinder

    override fun onCreate(savedInstanceState: Bundle?) {
        val presenterState = savedInstanceState?.getBundle(KEY_PRESENTER_STATE)
        application.getComponent()
            .homeComponent(HomeModule(this, presenterState))
            .inject(activity = this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = SimpleRecyclerAdapter(adapterPresenter, binder)
        val view = HomeViewImpl(window.decorView, adapter)

        presenter.attachView(view)
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachRouter(this)
    }

    override fun onStop() {
        presenter.detachRouter()
        super.onStop()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBundle(KEY_PRESENTER_STATE, presenter.saveState())
    }

    override fun openChatScreen(chatId: Int) {
        val intent = createChatActivityIntent(context = this, chatId = chatId)
        startActivity(intent)
    }

    override fun leaveScreen() {
        finish()
    }

}

private const val KEY_PRESENTER_STATE = "presenter_state"
