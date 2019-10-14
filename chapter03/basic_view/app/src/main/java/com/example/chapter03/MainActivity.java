package com.example.chapter03;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mClassButton, mInterfaceButton;
    ImageView mReplaceImage;
    ProgressBar mHorizontalProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mClassButton=findViewById(R.id.anonymous_class_button);
        mInterfaceButton=findViewById(R.id.interface_button);
        mReplaceImage=findViewById(R.id.image_replace);
        mHorizontalProgressBar=findViewById(R.id.progress_horizontal);
        mClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,getString(R.string.button_onClick_register2),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickButton(View view) {
        Toast.makeText(this,getString(R.string.button_onClick_register1),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.interface_button:
                Toast.makeText(this,getString(R.string.button_onClick_register3),Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    public void image_replace_button(View view) {
        if (mReplaceImage.getDrawable()==getResources().getDrawable(R.drawable.bridge))
            mReplaceImage.setImageResource(R.drawable.tunnel);
        else
            mReplaceImage.setImageResource(R.drawable.bridge);
    }

    public void progress_add_button(View view) {
        int progress=mHorizontalProgressBar.getProgress();
        mHorizontalProgressBar.setProgress(progress+10);
    }

    public void alert_dialog_pop_button(View view) {
        AlertDialog.Builder mAlertDialog=new AlertDialog.Builder(this)
                .setTitle("Alert Dialog")
                .setMessage("Something important!")
                .setCancelable(false)
                .setPositiveButton(getString(R.string.alert_dialog_positive), new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),getString(R.string.alert_dialog_pos_message),Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(getString(R.string.alert_dialog_negative), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,getString(R.string.alert_dialog_neg_message),Toast.LENGTH_SHORT).show();
                    }
                });
        mAlertDialog.show();
    }

    public void progress_dialog_pop_button(View view) {
        ProgressDialog mProgressDialog=new ProgressDialog(this);
        mProgressDialog.setTitle("Progress Dialog");
        mProgressDialog.setMessage("Something doing");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }
}
