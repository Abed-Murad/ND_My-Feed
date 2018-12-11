package com.am.my_feed.feed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.am.my_feed.room.Article;
import com.am.my_feed.databinding.CardFeedArticleBinding;
import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 1;


    private Context mContext;
    private List<Article> mArticleList;
    private LayoutInflater mInflater;

    private OnArticleClickListener mArticleClickListener;


    public FeedAdapter(Context context, OnArticleClickListener articleClickListener) {
        this.mContext = context;
        this.mArticleList = new ArrayList<>();
        this.mArticleClickListener = articleClickListener;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ITEM: {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                CardFeedArticleBinding cardBinding = CardFeedArticleBinding.inflate(inflater, parent, false);
                return new ViewHolder(cardBinding);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ViewHolder && mArticleList != null) {
            if (mArticleList.size() != 0) {
            final Article article = getItem(position);
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.bindData(article);
            }

        }
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return mArticleList == null ? 0 : mArticleList.size();
    }

    public void add(Article article) {
        mArticleList.add(article);
        notifyItemInserted(mArticleList.size());
    }

    public void addAll(List<Article> articleList) {
        for (Article article : articleList) {
            add(article);
            Logger.d(article);
        }
    }

    private Article getItem(int position) {
        return mArticleList.get(position);
    }

    public void SetOnItemClickListener(final OnArticleClickListener mItemClickListener) {
        this.mArticleClickListener = mItemClickListener;
    }

    public interface OnArticleClickListener {
        //TODO: use this method in the real project data not the parameter-less onItemClick()
        void onItemClick(View view, int position, Article model);

        void onBookmarkButtonClick(Article model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CardFeedArticleBinding mBinding;

        public ViewHolder(CardFeedArticleBinding itemView) {
            super(itemView.getRoot());
            this.mBinding = itemView;
            mBinding.getRoot().setOnClickListener(view -> mArticleClickListener.onItemClick(mBinding.getRoot(), getAdapterPosition(), getItem(getAdapterPosition())));//TODO: Remove this Dummy Data

        }

        private void bindData(Article article) {
            mBinding.setArticle(article);
            Glide.with(mContext).load(article.getUrlToImage()).into(mBinding.articleImage);
            mBinding.executePendingBindings();
        }
    }


}
