package com.tomclaw.vika;

import android.app.Application;

import com.tomclaw.vika.core.VkApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by solkin on 05/11/2017.
 */
public class Vika extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.vk.com/method/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        VkApi api = retrofit.create(VkApi.class);
    }
}
