package com.tomclaw.vika.core;

import android.content.Context;
import android.text.TextUtils;

import com.tomclaw.minion.Minion;
import com.tomclaw.minion.ResultCallback;
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

    private UserData userData;

    private File userFile;

    public static UserHolder create(Context context) {
        UserHolder userHolder = new UserHolder(context);
        userHolder.load();
        return userHolder;
    }

    private UserHolder(Context context) {
        userData = new UserData();
        File storage = context.getDir(STORAGE_FOLDER, Context.MODE_PRIVATE);
        userFile = new File(storage, USER_FILE);
    }

    private void load() {
        try {
            Minion minion = Minion.lets()
                    .load(FileStorage.create(userFile))
                    .sync();

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

    public void store() {
        Minion.lets()
                .store(FileStorage.create(userFile))
                .async(new ResultCallback() {
                    @Override
                    public void onReady(Minion minion) {
                        minion.setValue(USER_GROUP_NAME, KEY_USER_ID, userData.getUserId());
                        minion.setValue(USER_GROUP_NAME, KEY_ACCESS_TOKEN, userData.getAccessToken());
                        minion.setValue(USER_GROUP_NAME, KEY_EXPIRES_IN, Long.toString(userData.getExpiresIn()));
                    }

                    @Override
                    public void onFailure(Exception ex) {
                        Logger.log("unable to store user data");
                    }
                });
    }

    public UserData getUserData() {
        return userData;
    }
}
