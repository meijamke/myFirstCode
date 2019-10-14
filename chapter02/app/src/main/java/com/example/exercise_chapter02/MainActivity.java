package com.example.exercise_chapter02;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    public static final String KEY_STRING_DATA_TO_RIDA="string data to ReceiveIntentDataActivity";
    public static final int REQUEST_CODE_MA_TO_RIDA=1;

    private static final String KEY_BUNDLE_DATA="bundle key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState!=null) {
            String bundleData = savedInstanceState.getString(KEY_BUNDLE_DATA);
            Toast.makeText(this, bundleData, Toast.LENGTH_SHORT).show();
        }
    }

    //点击按钮弹出Toast
    public void toastButton(View view) {
        Toast.makeText(this,"This is a long toast",Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Toast.makeText(this,"This is add menu item",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_remove:
                Toast.makeText(this, "This is remove menu item", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    //点击按钮结束当前活动，并杀掉应用的进程
    public void backButton(View view) {
        finish();//结束当前活动
        android.os.Process.killProcess(android.os.Process.myPid());//杀掉应用进程
    }

    //点击按钮打开网页
    public void webButton(View view) {
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://github.com"));
        startActivity(intent);
    }

    //点击按钮打开电话本
    public void dialButton(View view) {
        Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:10088"));
        startActivity(intent);
    }

    public void sendDataButton(View view) {
        Toast.makeText(this,"hello guy~",Toast.LENGTH_SHORT).show();
        startReceiveIntentDataActivity(this,"hello guy~",REQUEST_CODE_MA_TO_RIDA);
    }

    //将启动活动的代码封装成函数，方便阅读
    private void startReceiveIntentDataActivity(Context context, String data, int requestCode){
        Intent intent=new Intent(context,ReceiveIntentDataActivity.class);
        intent.putExtra(KEY_STRING_DATA_TO_RIDA,data);
        startActivityForResult(intent,requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_MA_TO_RIDA)
            switch (resultCode) {
                case ReceiveIntentDataActivity.RESULT_CODE_RDA_TO_MA:
                    if (data != null) {
                        String returnStr = data.getStringExtra(ReceiveIntentDataActivity.KEY_STRING_DATA_TO_MA);
                        Toast.makeText(this, returnStr, Toast.LENGTH_LONG).show();
                    }
                    break;
                default:
                    break;
            }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String data="Hi, bundle guy, don't worry, every key info has been saved~";
        Toast.makeText(this,data,Toast.LENGTH_LONG).show();
        outState.putString(KEY_BUNDLE_DATA,data);
    }

    public void finishAllActivityButton(View view) {
        ActivityCollector.finishAll();
    }
}
