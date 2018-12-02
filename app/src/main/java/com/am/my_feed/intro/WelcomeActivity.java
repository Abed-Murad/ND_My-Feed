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
import android.widget.ImageView;
import android.widget.TextView;

import com.am.my_feed.R;
import com.am.my_feed.activity.MainActivity;
import com.am.my_feed.databinding.ActivityWelcomeBinding;
import com.am.my_feed.databinding.FragmentWelcomeBinding;
import com.am.my_feed.profile.ProfileFragment;

public class WelcomeActivity extends AppCompatActivity implements ProfileFragment.OnFragmentInteractionListener {
    private final static int NUM_ITEMS = 4;

    private ActivityWelcomeBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        mBinding.guestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
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
                    return WelcomeFragment.newInstance("Breaking News ",
                            "We track headlines in 7 categories across over 50 countries, " +
                                    "and at over a hundred top publications and blogs, in near real time.",
                            R.drawable.ic_globe);
                case 1:
                    return WelcomeFragment.newInstance("Favourite Articles",
                            "My feed provide an offline mode so you can save your favorite articles and read them latter ", R.drawable.ic_bookmark);
                case 2:
                    return WelcomeFragment.newInstance("Search",
                            "Search articles that mention a specific topic or keyword, we index every article published by over 30,000 news sources and blogs." ,
                            R.drawable.ic_search);
                case 3:
                    return WelcomeFragment.newInstance("Keep in touch",
                            "Get notification on new articles ",
                            R.drawable.ic_notifications);
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
    }


    public static class WelcomeFragment extends Fragment {

        private FragmentWelcomeBinding mBinding;
        private TextView mTitleTextView;
        private TextView mSubTitleTextView;
        private ImageView mIconImageView;

        private String mTitle;
        private String mSubtitle;
        private int mIcon;

        /**
         * Create a new instance of WelcomeFragment, providing "num"
         * as an argument.
         */
        public static WelcomeFragment newInstance(String title, String subtitle, int icon) {
            WelcomeFragment welcomeFragment = new WelcomeFragment();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putString("title", title);
            args.putString("subtitle", subtitle);
            args.putInt("icon", icon);
            welcomeFragment.setArguments(args);
            return welcomeFragment;
        }

        /**
         * When creating, retrieve this instance's number from its arguments.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mTitle = getArguments() != null ? getArguments().getString("title") : "No Title Found";
            mSubtitle = getArguments() != null ? getArguments().getString("subtitle") : "No Subtitle Found";
            mIcon = getArguments() != null ? getArguments().getInt("icon") : 1;
        }

        /**
         * Bind the data to the fragment Ui elements
         */
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
