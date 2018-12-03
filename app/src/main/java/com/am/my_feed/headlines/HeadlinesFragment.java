package com.am.my_feed.headlines;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.am.my_feed.R;
import com.am.my_feed.databinding.FragmentHeadlinesBinding;
import com.am.my_feed.feed.FeedFragment;
import com.am.my_feed.util.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HeadlinesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HeadlinesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HeadlinesFragment extends BaseFragment {
    private final static String fragmentsTitles[] =
            {"Business", "entertainment", "general", "health", "general", "sports", "technology"};
    private static final String ARG_PARAM2 = "param2";

    private FragmentHeadlinesBinding mBinding;
    private String mTitleParam;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HeadlinesFragment() {
        // Required empty public constructor
    }

    public static HeadlinesFragment newInstance(String param1, String param2) {
        HeadlinesFragment fragment = new HeadlinesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitleParam = getArguments().getString(ARG_TITLE);
            onFragmentInteraction(mTitleParam);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_headlines, container, false);
        HeadlinesAdapter adapter = new HeadlinesAdapter(getChildFragmentManager(), fragmentsTitles);
        mBinding.headlinesDeckPager.setAdapter(adapter);
        mBinding.tabs.setViewPager(mBinding.headlinesDeckPager);
        return mBinding.getRoot();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onFragmentInteraction(String title) {
        if (mListener != null) {
            mListener.onFragmentInteraction(title);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String title);
    }

    private static class HeadlinesAdapter extends FragmentPagerAdapter {
        private String mFragmentsTitles[];


        public HeadlinesAdapter(FragmentManager fragmentManager, String fragmentsTitles[]) {
            super(fragmentManager);
            this.mFragmentsTitles = fragmentsTitles;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return FeedFragment.newInstance("", "");
                case 1:
                    return FeedFragment.newInstance("", "");
                case 2:
                    return FeedFragment.newInstance("", "");
                case 3:
                    return FeedFragment.newInstance("", "");
                case 4:
                    return FeedFragment.newInstance("", "");
                case 5:
                    return FeedFragment.newInstance("", "");
                case 6:
                    return FeedFragment.newInstance("", "");
                default:
                    return FeedFragment.newInstance("", "");
            }
        }

        @Override
        public int getCount() {
            return mFragmentsTitles.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentsTitles[position];
        }
    }
}
