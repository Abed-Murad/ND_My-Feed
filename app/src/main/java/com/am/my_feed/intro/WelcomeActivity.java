package com.am.my_feed.intro;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.am.my_feed.R;
import com.am.my_feed.databinding.ActivityWelcomeBinding;
import com.am.my_feed.databinding.FragmentWelcomeBinding;
import com.am.my_feed.main.MainActivity;
import com.am.my_feed.profile.ProfileFragment;

public class WelcomeActivity extends AppCompatActivity implements ProfileFragment.OnFragmentInteractionListener {

    private final static int NUM_FRAGMENTS = 4;
    private ActivityWelcomeBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        mBinding.guestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
        });
        mBinding.deckPager.setAdapter(new WelcomePagerAdapter(getSupportFragmentManager()));
        mBinding.dotsIndicator.setViewPager(mBinding.deckPager);
    }

    @Override
    public void onFragmentInteraction(String title) {

    }

    private class WelcomePagerAdapter extends FragmentPagerAdapter {

        public WelcomePagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return WelcomeFragment.newInstance(getString(R.string.title_breaking_news),
                            getString(R.string.subtitle_breaking_news),
                            R.drawable.ic_globe);
                case 1:
                    return WelcomeFragment.newInstance(getString(R.string.title_favourite_articles),
                            getString(R.string.subtitle_favourite_articles),
                            R.drawable.ic_bookmark);
                case 2:
                    return WelcomeFragment.newInstance(getString(R.string.title_search),
                            getString(R.string.title_search_for_articles),
                            R.drawable.ic_search);
                case 3:
                    return WelcomeFragment.newInstance(getString(R.string.title_keep_in_touch),
                            getString(R.string.subtitle_keep_in_touch),
                            R.drawable.ic_notifications);
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_FRAGMENTS;
        }
    }


    public static class WelcomeFragment extends Fragment {
        public static final String KEY_TITLE = "title";
        public static final String KEY_SUBTITLE = "subtitle";
        public static final String KEY_ICON = "icon";
        private FragmentWelcomeBinding mBinding;

        private String mTitle;
        private String mSubtitle;
        private int mIcon;

        public static WelcomeFragment newInstance(String title, String subtitle, int icon) {
            WelcomeFragment welcomeFragment = new WelcomeFragment();
            // Supply Fragment arguments.
            Bundle args = new Bundle();
            args.putString(KEY_TITLE, title);
            args.putString(KEY_SUBTITLE, subtitle);
            args.putInt(KEY_ICON, icon);
            welcomeFragment.setArguments(args);
            return welcomeFragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mTitle = getArguments() != null ? getArguments().getString(KEY_TITLE) : "No Title Found";
            mSubtitle = getArguments() != null ? getArguments().getString(KEY_SUBTITLE) : "No Subtitle Found";
            mIcon = getArguments() != null ? getArguments().getInt(KEY_ICON) : -1;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false);

            mBinding.title.setText(mTitle);
            mBinding.subtitle.setText(mSubtitle);
            mBinding.icon.setImageResource(mIcon);

            return mBinding.getRoot();
        }


    }


}
