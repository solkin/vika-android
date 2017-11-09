package com.tomclaw.vika.core;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.tomclaw.vika.util.Unobfuscatable;

/**
 * Created by Igor on 07.07.2015.
 */
public class UserData implements Unobfuscatable {

    private @Nullable String accessToken;
    private long expiresIn;
    private @Nullable String userId;

    public UserData() {
    }

    public UserData(@NonNull String accessToken, long expiresIn, @NonNull String userId) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.userId = userId;
    }

    @Nullable
    public String getAccessToken() {
        return accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    @Nullable
    public String getUserId() {
        return userId;
    }
}
