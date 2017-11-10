package com.tomclaw.vika.di;

import android.content.Context;

import com.tomclaw.vika.core.UserHolder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Igor on 10.11.2017.
 */
@Module
public class UserModule {

    @Provides
    @Singleton
    public UserHolder provideUserHolder(Context context) {
        return UserHolder.create(context);
    }
}
