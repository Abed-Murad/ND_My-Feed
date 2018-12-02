package com.am.my_feed.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;

import com.am.my_feed.R;
import com.am.my_feed.databinding.ActivityMainBinding;
import com.am.my_feed.favorite.FavoriteFragment;
import com.am.my_feed.feed.FeedFragment;
import com.am.my_feed.headlines.HeadlinesFragment;
import com.am.my_feed.profile.ProfileFragment;
import com.am.my_feed.search.SearchFragment;
import com.am.my_feed.util.BaseActivity;
import com.orhanobut.logger.Logger;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;

public class MainActivity extends BaseActivity implements
        FeedFragment.OnFragmentInteractionListener,
        HeadlinesFragment.OnFragmentInteractionListener,
        FavoriteFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener ,
        SearchFragment.OnFragmentInteractionListener{

    private NavController mNavController;
    private BottomNavigationView mBottomNavView;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setupBottomNavigation();
    }

    private void setupBottomNavigation() {
        mBottomNavView = mBinding.bottomNavigationView;
        mNavController = Navigation.findNavController(this, R.id.navHostFragment);
        setupActionBarWithNavController(this, mNavController);
        NavigationUI.setupWithNavController(mBottomNavView, mNavController);
    }


    @Override
    public void onFragmentInteraction(String fragmentTitle) {
        Logger.d(fragmentTitle);
    }
}
