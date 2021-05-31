package com.tomclaw.vika.screen.home.di

import android.content.Context
import android.os.Bundle
import com.avito.konveyor.ItemBinder
import com.avito.konveyor.adapter.AdapterPresenter
import com.avito.konveyor.adapter.SimpleAdapterPresenter
import com.avito.konveyor.blueprint.ItemBlueprint
import com.tomclaw.vika.core.Chats
import com.tomclaw.vika.screen.home.*
import com.tomclaw.vika.screen.home.adapter.chat.ChatItemBlueprint
import com.tomclaw.vika.screen.home.adapter.chat.ChatItemPresenter
import com.tomclaw.vika.util.PerActivity
import com.tomclaw.vika.util.SchedulersFactory
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
class HomeModule(
    private val context: Context,
    private val state: Bundle?
) {

    @Provides
    @PerActivity
    internal fun providePresenter(
        interactor: HomeInteractor,
        adapterPresenter: Lazy<AdapterPresenter>,
        chatConverter: ChatConverter,
        schedulers: SchedulersFactory
    ): HomePresenter = HomePresenterImpl(
        interactor,
        adapterPresenter,
        chatConverter,
        schedulers,
        state
    )

    @Provides
    @PerActivity
    internal fun provideInteractor(
        chats: Chats,
        schedulers: SchedulersFactory
    ): HomeInteractor = HomeInteractorImpl(chats, schedulers)

    @Provides
    @PerActivity
    internal fun provideResourceProvider(): ChatsResourceProvider {
        return ChatsResourceProviderImpl(context.resources)
    }

    @Provides
    @PerActivity
    internal fun provideChatConverter(resourceProvider: ChatsResourceProvider): ChatConverter {
        return ChatConverterImpl(resourceProvider)
    }

    @Provides
    @PerActivity
    internal fun provideAdapterPresenter(binder: ItemBinder): AdapterPresenter {
        return SimpleAdapterPresenter(binder, binder)
    }

    @Provides
    @PerActivity
    internal fun provideItemBinder(
        blueprintSet: Set<@JvmSuppressWildcards ItemBlueprint<*, *>>
    ): ItemBinder {
        return ItemBinder.Builder().apply {
            blueprintSet.forEach { registerItem(it) }
        }.build()
    }

    @Provides
    @IntoSet
    @PerActivity
    internal fun provideBookItemBlueprint(
        presenter: ChatItemPresenter
    ): ItemBlueprint<*, *> = ChatItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideBookItemPresenter(presenter: HomePresenter) =
        ChatItemPresenter(presenter)

}
