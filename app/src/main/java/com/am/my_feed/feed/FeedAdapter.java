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
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    private String mHeaderTitle;
    private String mFooterTitle;

    private Context mContext;
    private List<Article> mArticleList;
    private LayoutInflater mInflater;

    private OnHeaderClickListener mHeaderClickListener;
    private OnFooterClickListener mFooterClickListener;
    private OnArticleClickListener mArticleClickListener;

    public FeedAdapter(Context context, String headerTitle, String footerTitle) {
        this.mContext = context;
        this.mHeaderTitle = headerTitle;
        this.mFooterTitle = footerTitle;
        this.mArticleList = new ArrayList<>();
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case TYPE_HEADER: {
                View v = mInflater.inflate(R.layout.card_feed_header, parent, false);
                return new HeaderViewHolder(v);
            }
            case TYPE_FOOTER: {
                View v = mInflater.inflate(R.layout.card_feed_footer, parent, false);
                return new FooterViewHolder(v);
            }
            case TYPE_ITEM: {
                View v = mInflater.inflate(R.layout.card_feed_article, parent, false);
                return new ViewHolder(v);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            headerHolder.setHeaderData();

        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;
            footerHolder.SetFooterData();

        } else if (holder instanceof ViewHolder) {
            /*
            final Article article = getItem(position - 1);
            ViewHolder genericViewHolder = (ViewHolder) holder;
            genericViewHolder.bindData(article);
            */
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        } else if (isPositionFooter(position)) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return mArticleList == null ? 2 : mArticleList.size() + 10;//TODO:change the 4 to 2
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private boolean isPositionFooter(int position) {
        return position == mArticleList.size() + 1;
    }


    public void updateList(List<Article> modelList) {
        this.mArticleList = modelList;
        notifyDataSetChanged();
    }

    private Article getItem(int position) {
        return mArticleList.get(position);
    }

    public void add(Article article) {
        mArticleList.add(article);
        notifyItemInserted(mArticleList.size() - 1);
    }

    public void addFirst(Article article) {
        mArticleList.add(0, article);
        notifyItemInserted(0);

    }

    public void addAll(List<Article> appendedItemList) {
        if (appendedItemList == null || appendedItemList.size() <= 0) {
            return;
        }
        if (this.mArticleList == null) {
            this.mArticleList = new ArrayList<>();
        }
        this.mArticleList.addAll(appendedItemList);
        notifyDataSetChanged();
    }

    public void searchFilter(List<Article> list, String search) {
        this.mArticleList.clear();
        if (search == null || search.isEmpty()) {
            this.mArticleList.addAll(list);
        } else {
            for (Article article : list) {
                /*
                if (article.getTitle().toLowerCase().contains(search.toLowerCase())) {
                    this.mArticleList.add(article);
                }
                */
            }
        }
        notifyDataSetChanged();
    }

    public void searchFilter(String search) {
        this.mArticleList.clear();
        for (Article article : mArticleList) {
            /*
            if (article.getTitle().toLowerCase().contains(search.toLowerCase())) {
                this.mArticleList.add(article);
            }
            */
        }
        notifyDataSetChanged();
    }

    public List<Article> getDataSet() {
        return this.mArticleList;
    }


    public void SetOnItemClickListener(final OnArticleClickListener mItemClickListener) {
        this.mArticleClickListener = mItemClickListener;
    }

    public void SetOnHeaderClickListener(final OnHeaderClickListener headerClickListener) {
        this.mHeaderClickListener = headerClickListener;
    }

    public void SetOnFooterClickListener(final OnFooterClickListener footerClickListener) {
        this.mFooterClickListener = footerClickListener;
    }

    public interface OnArticleClickListener {
        void onItemClick(View view, int position, Article model);
    }

    public interface OnHeaderClickListener {
        void onHeaderClick(View view, String headerTitle);
    }

    public interface OnFooterClickListener {
        void onFooterClick(View view, String headerTitle);
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(final View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mFooterClickListener.onFooterClick(itemView, mFooterTitle);
                }
            });
        }

        private void SetFooterData() {
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mHeaderClickListener.onHeaderClick(itemView, mHeaderTitle);
                }
            });
        }

        private void setHeaderData() {
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mArticleClickListener.onItemClick(itemView, getAdapterPosition(), mArticleList.get(getAdapterPosition() - 1));
                }
            });
        }

        private void bindData(Article article) {
        }
    }


}
