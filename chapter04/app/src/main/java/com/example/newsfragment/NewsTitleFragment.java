package com.example.newsfragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsfragment.data.News;
import com.example.newsfragment.data.NewsDummyData;

public class NewsTitleFragment extends Fragment implements NewsTitleAdapter.OnClickNewsTitleListener{

    private boolean isTwoPane;
    private NewsTitleAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.news_title_frag,container,false);

        NewsDummyData.initNews();

        RecyclerView mRecyclerView=view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        mAdapter=new NewsTitleAdapter(getActivity(), NewsDummyData.mDummyNews,this);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isTwoPane = getActivity().findViewById(R.id.news_detail_layout) != null;
    }

    @Override
    public void onClickNewsTitle(int position) {
        News news=mAdapter.getNews(position);
        if (isTwoPane){
            NewsDetailFragment detailFrag=(NewsDetailFragment)getFragmentManager().findFragmentById(R.id.news_detail_fragment_sw600dp);
            detailFrag.refresh(news.getTitle(),news.getContent());
        }else
            NewsDetailActivity.toNewsDetailActivity(getActivity(),news.getTitle(),news.getContent());
    }
}
