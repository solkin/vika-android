package com.tomclaw.vika.di;

import android.content.Context;

import com.tomclaw.vika.core.UserHolder;
import com.tomclaw.vika.core.UserSession;
import com.tomclaw.vika.core.VkApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Igor on 10.11.2017.
 */
@Module
class UserModule {

    @Provides
    @Singleton
    UserHolder provideUserHolder(Context context) {
        return UserHolder.create(context);
    }

    @Provides
    @Singleton
    UserSession provideUserSession(UserHolder userHolder, VkApi api) {
        return new UserSession(userHolder, api);
    }
}
