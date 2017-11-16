package com.tomclaw.vika.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.tomclaw.minion.Minion;
import com.tomclaw.minion.storage.FileStorage;
import com.tomclaw.vika.util.Logger;

import java.io.File;

/**
 * Created by Igor on 09.11.2017.
 */
public class UserHolder {

    private static final String STORAGE_FOLDER = "user";
    private static final String USER_FILE = "user.dat";
    private static final String USER_GROUP_NAME = "User";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_EXPIRES_IN = "expires_in";

    private @Nullable UserData userData;

    private File userFile;
    private Minion minion;

    public static UserHolder create(Context context) {
        UserHolder userHolder = new UserHolder(context);
        FileStorage storage = FileStorage.create(userHolder.userFile);
        userHolder.minion = Minion.lets()
                .load(storage)
                .and()
                .store(storage)
                .sync();
        userHolder.load();
        return userHolder;
    }

    private UserHolder(Context context) {
        File storage = context.getDir(STORAGE_FOLDER, Context.MODE_PRIVATE);
        userFile = new File(storage, USER_FILE);
    }

    private void load() {
        try {
            String userId = minion.getValue(USER_GROUP_NAME, KEY_USER_ID);
            String accessToken = minion.getValue(USER_GROUP_NAME, KEY_ACCESS_TOKEN);
            String expiresIn = minion.getValue(USER_GROUP_NAME, KEY_EXPIRES_IN);

            if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(accessToken) && !TextUtils.isEmpty(expiresIn)) {
                userData = new UserData(accessToken, Long.parseLong(expiresIn), userId);
            }
        } catch (Throwable ex) {
            Logger.log("unable to load user data");
        }
    }

    public void hold(@NonNull final UserData userData) {
        this.userData = userData;
        minion.setValue(USER_GROUP_NAME, KEY_USER_ID, userData.getUserId());
        minion.setValue(USER_GROUP_NAME, KEY_ACCESS_TOKEN, userData.getAccessToken());
        minion.setValue(USER_GROUP_NAME, KEY_EXPIRES_IN, Long.toString(userData.getExpiresIn()));
        minion.store();
    }

    public @Nullable UserData getUserData() {
        return userData;
    }

    public boolean isUnauthorized() {
        return userData == null;
    }
}
