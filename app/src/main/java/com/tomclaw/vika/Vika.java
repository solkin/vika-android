package com.tomclaw.vika;

import android.app.Application;

import com.tomclaw.vika.core.UserHolder;
import com.tomclaw.vika.core.VkApi;
import com.tomclaw.vika.di.AppComponent;
import com.tomclaw.vika.di.AppModule;
import com.tomclaw.vika.di.DaggerAppComponent;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by solkin on 05/11/2017.
 */
public class Vika extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = buildComponent();
    }

    public static AppComponent getComponent() {
        return component;
    }

    private AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
