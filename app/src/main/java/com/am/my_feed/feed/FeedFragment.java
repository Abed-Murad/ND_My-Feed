package com.am.my_feed.feed;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.am.my_feed.R;
import com.am.my_feed.article.Article;
import com.am.my_feed.article.ArticleList;
import com.am.my_feed.databinding.FragmentFeedBinding;
import com.am.my_feed.network.APIClient;
import com.am.my_feed.network.ApiRequests;
import com.am.my_feed.util.BaseFragment;
import com.am.my_feed.util.FUNC;
import com.orhanobut.logger.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.am.my_feed.util.CONST.TEST_ARTICLE_URL;


public class FeedFragment extends BaseFragment {
    private static final String ARG_PARAM2 = "param2";

    private FragmentFeedBinding mBinding;
    private RecyclerView mFeedRecyclerView;
    private FeedAdapter mFeedAdapter;

    private String mTitleParam;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Context mContext;

    public FeedFragment() {
        // Required empty public constructor
    }

    public static FeedFragment newInstance(String title, String param2) {
        FeedFragment fragment = new FeedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_PARAM2, param2);
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
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false);
        mFeedRecyclerView = mBinding.userFeedRecyclerView;



        ApiRequests apiService = APIClient.getClient().create(ApiRequests.class);
        apiService.getHeadlines().enqueue(new Callback<ArticleList>() {
            @Override
            public void onResponse(Call<ArticleList> call, Response<ArticleList> response) {
                mFeedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                Logger.d(response.body().getArticles());
                mFeedRecyclerView.setHasFixedSize(true);
                mFeedAdapter = new FeedAdapter(getContext(), new FeedAdapter.OnArticleClickListener() {
                    @Override
                    public void onItemClick(View view, int position, Article model) {
                        FUNC.openUrlInChromeCustomTab(mContext, null, TEST_ARTICLE_URL);
                    }

                    @Override
                    public void onBookmarkButtonClick() {

                    }
                });
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
