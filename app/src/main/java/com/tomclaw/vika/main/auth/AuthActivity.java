package com.tomclaw.vika.main.auth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tomclaw.vika.R;
import com.tomclaw.vika.core.Config;
import com.tomclaw.vika.util.UrlBuilder;
import com.tomclaw.vika.util.UrlParser;

import java.net.URI;
import java.util.Map;

/**
 * Created by solkin on 05/11/2017.
 */
public class AuthActivity extends AppCompatActivity {

    private static final String REDIRECT_URL = "https://oauth.vk.com/blank.html";

    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_auth);

        webView = findViewById(R.id.web_view);

        initWebView();
    }

    private void initWebView() {
        String url = prepareAuthUrl();
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith(REDIRECT_URL)) {
                    onAuthorizationHandled(url);
                    return true;
                }
                return false;
            }
        });
    }

    private String prepareAuthUrl() {
        return UrlBuilder
                .create("https://oauth.vk.com/authorize")
                .appendParam("client_id", Config.CLIENT_ID)
                .appendParam("display", "mobile")
                .appendParam("redirect_uri", REDIRECT_URL)
                .appendParam("scope", Config.ACCESS_SCOPE)
                .appendParam("response_type", "token")
                .appendParam("v", Config.API_VERSION)
                .build();
    }

    private void onAuthorizationHandled(String url) {
        Map<String, String> params = UrlParser.parse(URI.create(url));
        String accessToken = params.get("access_token");
        String expiresIn = params.get("expires_in");
        String userId = params.get("user_id");

        finish();
    }
}
