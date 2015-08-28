package io.github.hanihashemi.podgir.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

import io.github.hanihashemi.podgir.base.BaseActivity;
import io.github.hanihashemi.podgir.fragment.NavigationDrawerFragment;
import io.github.hanihashemi.podgir.fragment.PodcastsFragment;

public class MainActivity extends BaseActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    @Override
    public int getLayoutResource() {
        return io.github.hanihashemi.podgir.R.layout.activity_main;
    }

    @Override
    public void customizeUI() {
        super.customizeUI();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(io.github.hanihashemi.podgir.R.id.navigation_drawer);
        mTitle = getTitle();

        mNavigationDrawerFragment.setUp(
                io.github.hanihashemi.podgir.R.id.navigation_drawer,
                (DrawerLayout) findViewById(io.github.hanihashemi.podgir.R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (position) {
            case 0:
                fragmentManager.beginTransaction()
                        .replace(io.github.hanihashemi.podgir.R.id.container, new PodcastsFragment())
                        .commit();
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //noinspection deprecation
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(mTitle);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(io.github.hanihashemi.podgir.R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == io.github.hanihashemi.podgir.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
