package com.am.my_feed.feed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.am.my_feed.R;

import java.util.ArrayList;
import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 1;


    private Context mContext;
    private List<Article> mArticleList;
    private LayoutInflater mInflater;

    private OnArticleClickListener mArticleClickListener;


    public FeedAdapter(Context context) {
        this.mContext = context;
        this.mArticleList = new ArrayList<>();
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ITEM: {
                View v = mInflater.inflate(R.layout.card_feed_article, parent, false);
                return new ViewHolder(v);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ViewHolder && mArticleList != null) {
            /*
            final Article article = getItem(position - 1);
            ViewHolder genericViewHolder = (ViewHolder) holder;
            genericViewHolder.bindData(article);
            */
        }
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return mArticleList == null ? 0 : mArticleList.size() + 12; //TODO: Remove + 12
    }

    public void add(Article article) {
        mArticleList.add(article);
        notifyItemInserted(mArticleList.size() - 1);
    }

    public void SetOnItemClickListener(final OnArticleClickListener mItemClickListener) {
        /*
        this.mArticleClickListener = mItemClickListener; //TODO: Re Activate the OnArticleClickListener
        */
    }

    public interface OnArticleClickListener {
        void onItemClick(View view, int position, Article model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(view -> mArticleClickListener.onItemClick(itemView, getAdapterPosition(), mArticleList.get(getAdapterPosition() - 1)));
        }

        private void bindData(Article article) { }
    }


}
