package com.tomclaw.vika.di;

import com.tomclaw.vika.main.auth.AuthActivity;
import com.tomclaw.vika.main.home.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Igor on 10.11.2017.
 */
@Singleton
@Component(modules = {AppModule.class, UserModule.class, ApiModule.class})
public interface AppComponent {

    void inject(MainActivity activity);

    void inject(AuthActivity activity);

}
