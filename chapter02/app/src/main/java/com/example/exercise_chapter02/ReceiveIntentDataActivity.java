package com.example.exercise_chapter02;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class ReceiveIntentDataActivity extends BaseActivity {

    public static final String KEY_STRING_DATA_TO_MA="data return to MainActivity";
    public static final int RESULT_CODE_RDA_TO_MA=1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_intent_data);
    }

    public void returnDataButton(View view) {
        Intent intent=getIntent();
        String data=intent.getStringExtra(MainActivity.KEY_STRING_DATA_TO_RIDA);
        Intent myIntent=new Intent();
        myIntent.putExtra(KEY_STRING_DATA_TO_MA,"Hi man, I have received your info:"+data);
        setResult(RESULT_CODE_RDA_TO_MA,myIntent);
        finish();//结束当前活动，返回上一个活动（任务栈）
    }
}
