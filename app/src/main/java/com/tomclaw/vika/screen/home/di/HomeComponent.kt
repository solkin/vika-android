package com.tomclaw.vika.screen.home.di

import com.tomclaw.vika.screen.home.HomeActivity
import com.tomclaw.vika.util.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {

    fun inject(activity: HomeActivity)

}