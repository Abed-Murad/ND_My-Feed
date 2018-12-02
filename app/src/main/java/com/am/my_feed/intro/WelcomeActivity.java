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
import android.widget.TextView;

import com.am.my_feed.R;
import com.am.my_feed.activity.MainActivity;
import com.am.my_feed.databinding.ActivityWelcomeBinding;
import com.am.my_feed.profile.ProfileFragment;

public class WelcomeActivity extends AppCompatActivity implements ProfileFragment.OnFragmentInteractionListener {
    private final static int NUM_ITEMS = 3;

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
                    return ArrayListFragment.newInstance("First", "First Subtitle", R.drawable.ic_bookmark);
                case 1:
                    return ArrayListFragment.newInstance("Second", "Second Subtitle", R.drawable.ic_bookmark);
                case 2:
                    return ArrayListFragment.newInstance("Third", "Third Subtitle", R.drawable.ic_bookmark);
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
    }


    public static class ArrayListFragment extends Fragment {
        private String mTitle;
        private String mSubtitle;
        private int mIcon;

        /**
         * Create a new instance of CountingFragment, providing "num"
         * as an argument.
         */
        public static ArrayListFragment newInstance(String title, String subtitle, int icon) {
            ArrayListFragment arrayListFragment = new ArrayListFragment();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putString("title", title);
            args.putString("subtitle", subtitle);
            args.putInt("icon", icon);
            arrayListFragment.setArguments(args);
            return arrayListFragment;
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
         * The Fragment's UI is just a simple text view showing its
         * instance number.
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_pager_welcome, container, false);
            View tv = rootView.findViewById(R.id.title);
            ((TextView) tv).setText("Fragment #" + mTitle);
            return rootView;
        }


    }


}
