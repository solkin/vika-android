package com.tomclaw.mandarinx.screen.home.di

import com.tomclaw.mandarinx.screen.home.HomeActivity
import com.tomclaw.mandarinx.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {

    fun inject(activity: HomeActivity)

}