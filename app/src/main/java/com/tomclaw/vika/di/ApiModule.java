package com.tomclaw.vika.di;

import android.content.Context;

import com.tomclaw.vika.core.VkApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Igor on 10.11.2017.
 */
@Module
public class ApiModule {

    @Provides
    @Singleton
    public VkApi provideApi(Context context) {
        return new Retrofit.Builder()
                .baseUrl("https://api.vk.com/method/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(VkApi.class);
    }
}
