package com.example.self_define_widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TitleBarActivity extends LinearLayout {

    public TitleBarActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_bar,this);
        Button mBackButton=findViewById(R.id.back_button);
        Button mEditButton=findViewById(R.id.edit_button);
        mBackButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });
        mEditButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),
                        getContext().getString(R.string.action_edit), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
