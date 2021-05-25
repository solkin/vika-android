package com.tomclaw.vika.screen.home.di

import android.content.Context
import android.os.Bundle
import com.avito.konveyor.ItemBinder
import com.avito.konveyor.adapter.AdapterPresenter
import com.avito.konveyor.adapter.SimpleAdapterPresenter
import com.avito.konveyor.blueprint.ItemBlueprint
import com.tomclaw.vika.core.Dialogs
import com.tomclaw.vika.screen.home.*
import com.tomclaw.vika.screen.home.adapter.dialog.DialogItemBlueprint
import com.tomclaw.vika.screen.home.adapter.dialog.DialogItemPresenter
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
        dialogConverter: DialogConverter,
        schedulers: SchedulersFactory
    ): HomePresenter = HomePresenterImpl(
        interactor,
        adapterPresenter,
        dialogConverter,
        schedulers,
        state
    )

    @Provides
    @PerActivity
    internal fun provideInteractor(
        dialogs: Dialogs,
        schedulers: SchedulersFactory
    ): HomeInteractor = HomeInteractorImpl(dialogs, schedulers)

    @Provides
    @PerActivity
    internal fun provideResourceProvider(): DialogsResourceProvider {
        return DialogsResourceProviderImpl(context.resources)
    }

    @Provides
    @PerActivity
    internal fun provideDialogConverter(resourceProvider: DialogsResourceProvider): DialogConverter {
        return DialogConverterImpl(resourceProvider)
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
        presenter: DialogItemPresenter
    ): ItemBlueprint<*, *> = DialogItemBlueprint(presenter)

    @Provides
    @PerActivity
    internal fun provideBookItemPresenter(presenter: HomePresenter) =
        DialogItemPresenter(presenter)

}