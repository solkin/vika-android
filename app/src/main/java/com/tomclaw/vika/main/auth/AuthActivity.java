package com.tomclaw.vika.main.auth;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.tomclaw.vika.R;
import com.tomclaw.vika.Vika;
import com.tomclaw.vika.core.Config;
import com.tomclaw.vika.core.UserData;
import com.tomclaw.vika.core.UserHolder;
import com.tomclaw.vika.util.Logger;
import com.tomclaw.vika.util.UrlBuilder;
import com.tomclaw.vika.util.UrlParser;

import java.net.URI;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by solkin on 05/11/2017.
 */
public class AuthActivity extends AppCompatActivity {

    private static final String REDIRECT_URL = "https://oauth.vk.com/blank.html";

    @Inject
    UserHolder userHolder;

    private WebView webView;
    private ViewFlipper viewFlipper;
    private AppCompatImageView errorIcon;
    private TextView errorText;
    private View retryButton;

    private boolean isError = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Vika.getComponent().inject(this);

        setContentView(R.layout.activity_auth);

        webView = findViewById(R.id.web_view);
        viewFlipper = findViewById(R.id.view_flipper);
        errorIcon = findViewById(R.id.error_icon);
        errorText = findViewById(R.id.error_text);
        retryButton = findViewById(R.id.retry_button);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.auth_title);

        initWebView();
    }

    private void initWebView() {
        clearCookies(this);
        webView.clearCache(true);
        webView.clearHistory();
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                if (!isError) {
                    showContent();
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Logger.log(failingUrl);
                showError(R.string.network_error, (buttonView) -> loadAuthUrl());
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith(REDIRECT_URL)) {
                    onAuthorizationHandled(url);
                    return true;
                }
                return false;
            }

        });
        loadAuthUrl();
    }

    private void loadAuthUrl() {
        String url = prepareAuthUrl();
        loadUrl(url);
    }

    private void loadUrl(String url) {
        showProgress();
        webView.loadUrl(url);
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

        if (TextUtils.isEmpty(userId) || TextUtils.isEmpty(accessToken) || TextUtils.isEmpty(expiresIn)) {
            showError(R.string.auth_error, (retryButton) -> loadAuthUrl());
            return;
        }

        userHolder.hold(new UserData(accessToken, Long.parseLong(expiresIn), userId));
        finish();
    }

    private void showProgress() {
        isError = false;
        viewFlipper.setDisplayedChild(0);
    }

    private void showContent() {
        viewFlipper.setDisplayedChild(1);
    }

    private void showError(@StringRes int message, View.OnClickListener listener) {
        isError = true;
        viewFlipper.setDisplayedChild(2);
        errorIcon.setImageResource(R.drawable.ic_network_error);
        errorText.setText(message);
        retryButton.setOnClickListener(listener);
    }

    @SuppressWarnings("deprecation")
    public static void clearCookies(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            Logger.log("Using clearCookies code for API >=" + String.valueOf(Build.VERSION_CODES.LOLLIPOP_MR1));
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else {
            Logger.log("Using clearCookies code for API <" + String.valueOf(Build.VERSION_CODES.LOLLIPOP_MR1));
            CookieSyncManager syncManager = CookieSyncManager.createInstance(context);
            syncManager.startSync();
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            syncManager.stopSync();
            syncManager.sync();
        }
    }
}
