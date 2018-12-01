package com.am.myfeed.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;

import com.am.myfeed.R;
import com.am.myfeed.favoritearticle.FavoriteArticlesFragment;
import com.am.myfeed.headlines.HeadlinesFeedFragment;
import com.am.myfeed.profile.ProfileFragment;
import com.am.myfeed.userfeed.UserFeedFragment;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends BaseActivity implements UserFeedFragment.OnFragmentInteractionListener, HeadlinesFeedFragment.OnFragmentInteractionListener, FavoriteArticlesFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener {

    private NavController mNavController;
    BottomNavigationView mBottomNavView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomNavView = findViewById(R.id.bottomNavigationView);
        mNavController = Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupWithNavController(mBottomNavView, mNavController);
    }



    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
