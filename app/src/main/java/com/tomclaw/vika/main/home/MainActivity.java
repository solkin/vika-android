package com.tomclaw.vika.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.tomclaw.vika.R;
import com.tomclaw.vika.main.auth.AuthActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dialogs:
                    textMessage.setText(R.string.title_dialogs);
                    return true;
                case R.id.navigation_contacts:
                    textMessage.setText(R.string.title_contacts);
                    return true;
                case R.id.navigation_account:
                    textMessage.setText(R.string.title_account);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
    }

}
