package com.am.my_feed.search;

import android.app.SearchManager;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.am.my_feed.R;
import com.am.my_feed.databinding.FragmentSearchBinding;
import com.am.my_feed.profile.ProfileFragment;
import com.am.my_feed.util.BaseFragment;


public class SearchFragment extends BaseFragment {
    private static final String ARG_PARAM2 = "param2";
    private String mTitleParam;
    private String mParam2;
    private FragmentSearchBinding mBidning;


    private OnFragmentInteractionListener mListener;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        mBidning = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        // Assumes current activity is the searchable activity
        mBidning.searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        mBidning.searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        mBidning.searchView.setSubmitButtonEnabled(true);
        mBidning.searchView.setQueryRefinementEnabled(true);

        return mBidning.getRoot();
    }

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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String title);
    }
}
