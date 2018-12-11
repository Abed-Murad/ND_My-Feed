package com.am.my_feed.favorite;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.am.my_feed.R;
import com.am.my_feed.databinding.FragmentFavoriteBinding;
import com.am.my_feed.feed.FeedAdapter;
import com.am.my_feed.room.Article;
import com.am.my_feed.room.ArticleListViewModel;
import com.am.my_feed.util.BaseFragment;

import java.util.List;


public class FavoriteFragment extends BaseFragment {
    private static final String ARG_PARAM2 = "param2";

    private FragmentFavoriteBinding mBidning;
    private String mTitleParam;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ArticleListViewModel articleListViewModel;
    private FeedAdapter mFeedAdapter;


    public FavoriteFragment() {
        // Required empty public constructor
    }

    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
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
        mBidning = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false);
        mBidning.favoriteRecyclerVIew.setLayoutManager(new LinearLayoutManager(getContext()));
        mFeedAdapter = new FeedAdapter(getContext(), new FeedAdapter.OnArticleClickListener() {
            @Override
            public void onItemClick(View view, int position, Article model) {

            }

            @Override
            public void onBookmarkButtonClick(Article article) {

            }
        });
        articleListViewModel = ViewModelProviders.of(this).get(ArticleListViewModel.class);
        articleListViewModel.getArticlesList().observe(getActivity(), new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable List<Article> articles) {
                mFeedAdapter.addAll(articles);
                mBidning.favoriteRecyclerVIew.setAdapter(mFeedAdapter);

            }
        });



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
