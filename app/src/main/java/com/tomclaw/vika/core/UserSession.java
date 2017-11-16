package com.tomclaw.vika.core;

/**
 * Created by solkin on 16/11/2017.
 */
public class UserSession {

    private UserHolder userHolder;
    private VkApi api;

    public UserSession(UserHolder userHolder, VkApi api) {
        this.userHolder = userHolder;
        this.api = api;
    }
}
