package com.tomclaw.vika.screen.chat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tomclaw.vika.R
import com.tomclaw.vika.main.getComponent
import com.tomclaw.vika.screen.chat.di.ChatModule
import javax.inject.Inject

class ChatActivity : AppCompatActivity(), ChatPresenter.ChatRouter {

    @Inject
    lateinit var presenter: ChatPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        val presenterState = savedInstanceState?.getBundle(KEY_PRESENTER_STATE)
        application.getComponent()
            .chatComponent(ChatModule(this, presenterState))
            .inject(activity = this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val view = ChatViewImpl(window.decorView)

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

    override fun leaveScreen() {
        finish()
    }

}

fun createChatActivityIntent(
    context: Context,
    chatId: Int
): Intent = Intent(context, ChatActivity::class.java)
    .putExtra(EXTRA_CHAT_ID, chatId)

private const val KEY_PRESENTER_STATE = "presenter_state"

private const val EXTRA_CHAT_ID = "chat_id"
