package com.tomclaw.vika.core;

import com.tomclaw.vika.util.Logger;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;

/**
 * Created by solkin on 16/11/2017.
 */
public class UserSession {

    private UserHolder userHolder;
    private VkApi api;

    public UserSession(UserHolder userHolder, VkApi api) {
        this.userHolder = userHolder;
        this.api = api;
        attachUserHolder();
    }

    private void attachUserHolder() {
        userHolder.userDataChanges()
                .subscribeOn(mainThread())
                .subscribe(this::onUserDataChanged);
    }

    private void onUserDataChanged(UserData userData) {
        Logger.log("user session changed");
    }
}
