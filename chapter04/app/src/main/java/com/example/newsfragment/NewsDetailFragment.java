package com.example.newsfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NewsDetailFragment extends Fragment {

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_detail_frag,container,false);
        return view;
    }

    public void refresh(String title,String content){
        view.findViewById(R.id.news_detail_layout).setVisibility(View.VISIBLE);
        TextView mTitle=view.findViewById(R.id.news_title);
        mTitle.setText(title);
        TextView mContent=view.findViewById(R.id.news_content);
        mContent.setText(content);
    }
}
