package com.tomclaw.vika;

import android.app.Application;

import com.tomclaw.vika.di.AppComponent;
import com.tomclaw.vika.di.AppModule;
import com.tomclaw.vika.di.DaggerAppComponent;

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
