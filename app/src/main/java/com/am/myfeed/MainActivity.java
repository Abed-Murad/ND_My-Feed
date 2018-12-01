package com.am.myfeed;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.am.am_util.activity.BaseActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends BaseActivity implements UserFeedFragment.OnFragmentInteractionListener, HeadlinesFeedFragment.OnFragmentInteractionListener, FavoriteArticlesFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener {

    private NavController mNavController;
    BottomNavigationView mBottonNavView;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_my_feed:
//                    mNavController.navigate(R.id.userFeedFragment);
                    return true;
                case R.id.navigation_headlines:
//                    mNavController.navigate(R.id.headlinesFeedFragment);
                    return true;
                case R.id.navigation_favorites:
//                    mNavController.navigate(R.id.favoriteArticlesFragment);
                    return true;
                case R.id.navigation_profile:
//                    mNavController.navigate(R.id.profileFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottonNavView = (BottomNavigationView) findViewById(R.id.navigation);

        mNavController = Navigation.findNavController(this, R.id.fragment_nav_host);

        NavigationUI.setupWithNavController(mBottonNavView, mNavController);
//        mBottonNavView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }



    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
