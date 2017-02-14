package com.example.bottombar.sample;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity  {
    private TextView messageView;
    private BottomBarTab recents;
    private BottomBarTab favorites;
    private BottomBarTab nearby;
    private BottomBarTab friends;
    private BottomBarTab food;
    private int counterNearby;

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_color_and_font);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Medico");
//        actionBar.setLogo(R.drawable.ic_search_black_24dp);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

//        actionBar.setBackgroundDrawable(getDrawable(0));

        /*

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_favorites);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);*/

        messageView = (TextView) findViewById(R.id.messageView);
        counterNearby = 0;

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        recents = bottomBar.getTabWithId(R.id.tab_recents);
        favorites = bottomBar.getTabWithId(R.id.tab_favorites);
        nearby = bottomBar.getTabWithId(R.id.tab_nearby);
        friends = bottomBar.getTabWithId(R.id.tab_friends);
        food = bottomBar.getTabWithId(R.id.tab_food);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                messageView.setText(TabMessage.get(tabId, false));
                setBadgeCount(R.id.tab_nearby, ++counterNearby);
                setBadgeCount(tabId, 0);
            }

        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                Toast.makeText(getApplicationContext(), TabMessage.get(tabId, true), Toast.LENGTH_LONG).show();
                setBadgeCount(R.id.tab_nearby, ++counterNearby);
                setBadgeCount(tabId, 0);
            }
        });

        setBadgeCount(R.id.tab_nearby, 6);
        setBadgeCount(R.id.tab_recents, 1);
        setBadgeCount(R.id.tab_friends, 3);

    }

    private void setBadgeCount(@IdRes int menuItemId, int counter) {
        switch (menuItemId) {
            case R.id.tab_recents:
                recents.setBadgeCount(counter);
                break;
            case R.id.tab_favorites:
                favorites.setBadgeCount(counter);
                break;
            case R.id.tab_nearby:
                nearby.setBadgeCount(counter);
                counterNearby = counter;
                break;
            case R.id.tab_friends:
                friends.setBadgeCount(counter);
                break;
            case R.id.tab_food:
                food.setBadgeCount(counter);
                break;
        }
    }


}
