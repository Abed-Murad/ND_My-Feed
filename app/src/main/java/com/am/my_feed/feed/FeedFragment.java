package com.am.my_feed.feed;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.am.my_feed.R;
import com.am.my_feed.article.ArticleList;
import com.am.my_feed.databinding.FragmentFeedBinding;
import com.am.my_feed.room.AddArticleViewModel;
import com.am.my_feed.room.Article;
import com.am.my_feed.room.ArticleListViewModel;
import com.am.my_feed.util.BaseFragment;
import com.am.my_feed.util.FUNC;
import com.orhanobut.logger.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FeedFragment extends BaseFragment {
    private static final String ARG_FEED_CATEGORY = "feed_category";

    private AddArticleViewModel addArticleViewModel;

    private FragmentFeedBinding mBinding;
    private RecyclerView mFeedRecyclerView;
    private FeedAdapter mFeedAdapter;

    private String mTitleParam;
    private String mFeedCategory;

    private OnFragmentInteractionListener mListener;
    private Context mContext;

    public FeedFragment() {
        // Required empty public constructor
    }

    public static FeedFragment newInstance(String title, String feedCategory) {
        FeedFragment fragment = new FeedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_FEED_CATEGORY, feedCategory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        if (getArguments() != null) {
            mTitleParam = getArguments().getString(ARG_TITLE);
            onFragmentInteraction(mTitleParam);
            mFeedCategory = getArguments().getString(ARG_FEED_CATEGORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false);
        mFeedRecyclerView = mBinding.userFeedRecyclerView;
        addArticleViewModel = ViewModelProviders.of(this).get(AddArticleViewModel.class);



        mFeedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mFeedRecyclerView.setHasFixedSize(true);
        mFeedAdapter = new FeedAdapter(getContext(), new FeedAdapter.OnArticleClickListener() {
            @Override
            public void onItemClick(View view, int position, Article article) {
                FUNC.openUrlInChromeCustomTab(mContext, article, article.getUrl());
            }

            @Override
            public void onBookmarkButtonClick(Article article) {
                addArticleViewModel.addArticle(new Article(article.getPublishedAt(), article.getUrlToImage(), article.getTitle()));

            }
        });

        apiService.getHeadlines("us", mFeedCategory).enqueue(new Callback<ArticleList>() {
            @Override
            public void onResponse(Call<ArticleList> call, Response<ArticleList> response) {
                Logger.d(response);
                Logger.d(response.body().getArticles());

                mFeedAdapter.addAll(response.body().getArticles());
                mFeedRecyclerView.setAdapter(mFeedAdapter);

            }

            @Override
            public void onFailure(Call<ArticleList> call, Throwable t) {

            }
        });


        return mBinding.getRoot();
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
