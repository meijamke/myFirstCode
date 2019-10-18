package com.example.newsfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class NewsDetailActivity extends AppCompatActivity {

    public static final String KEY_TITLE="News Title";
    public static final String KEY_CONTENT="News Content";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Intent intent=getIntent();
        String title=intent.getStringExtra(KEY_TITLE);
        String content=intent.getStringExtra(KEY_CONTENT);
        NewsDetailFragment fragment=(NewsDetailFragment)getSupportFragmentManager().findFragmentById(R.id.news_detail_fragment);
        fragment.refresh(title,content);
    }

    public static void toNewsDetailActivity(Context context, String title, String content){
        Intent intent=new Intent(context,NewsDetailActivity.class);
        intent.putExtra(KEY_TITLE,title);
        intent.putExtra(KEY_CONTENT,content);
        context.startActivity(intent);
    }
}
