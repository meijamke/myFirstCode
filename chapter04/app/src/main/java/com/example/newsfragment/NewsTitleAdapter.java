package com.example.newsfragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newsfragment.data.News;

import java.util.List;

public class NewsTitleAdapter extends RecyclerView.Adapter<NewsTitleAdapter.NewsTitleViewHolder>{

    private List<News> mNews;
    private Context mContext;
    private OnClickNewsTitleListener titleListener;

    NewsTitleAdapter(Context context, List<News> newsList,OnClickNewsTitleListener onClickNewsTitleListener){
        mContext=context;
        titleListener=onClickNewsTitleListener;
        mNews=newsList;
    }

    public interface OnClickNewsTitleListener{
        void onClickNewsTitle(int position);
    }

    @NonNull
    @Override
    public NewsTitleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.list_item_news_title, viewGroup, false);
        return new NewsTitleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsTitleViewHolder holder, int position) {
        News news=mNews.get(position);
        holder.mNewsTitle.setText(news.getTitle());
    }

    @Override
    public int getItemCount() {
        if (mNews!=null)
            return mNews.size();
        return 0;
    }

    class NewsTitleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mNewsTitle;

        NewsTitleViewHolder(@NonNull View itemView) {
            super(itemView);
            mNewsTitle=itemView.findViewById(R.id.news_title_list_item);
            mNewsTitle.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            titleListener.onClickNewsTitle(getAdapterPosition());
        }
    }

    News getNews(int position){
        return mNews.get(position);
    }
}
