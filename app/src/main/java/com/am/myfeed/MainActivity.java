package com.am.myfeed;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.am.am_util.activity.BaseActivity;

import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

public class MainActivity extends BaseActivity implements UserFeedFragment.OnFragmentInteractionListener, HeadlinesFeedFragment.OnFragmentInteractionListener, FavoriteArticlesFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener {

    private NavController mNavController;
    BottomNavigationView bottonNavView;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_my_feed:
                    mNavController.navigate(R.id.userFeedFragment);
                    return true;
                case R.id.navigation_headlines:
                    mNavController.navigate(R.id.headlinesFeedFragment);
                    return true;
                case R.id.navigation_favorites:
                    mNavController.navigate(R.id.favoriteArticlesFragment);
                    return true;
                case R.id.navigation_profile:
                    mNavController.navigate(R.id.profileFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavController = Navigation.findNavController(MainActivity.this, R.id.fragment_nav_host);
        mNavController.addOnNavigatedListener(new NavController.OnNavigatedListener() {
            @Override
            public void onNavigated(@NonNull NavController controller, @NonNull NavDestination destination) {
                destination.getId();
                destination.getLabel().toString();
                destination.getNavigator();
                destination.getParent().getStartDestination();
            }
        });
        bottonNavView = (BottomNavigationView) findViewById(R.id.navigation);
        bottonNavView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
