package com.example.chat;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.chat.data.Message;
import com.example.chat.data.MessageDummyData;

public class MainActivity extends AppCompatActivity {

    private EditText mSendText;
    private ChatAdapter mAdapter;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSendText=findViewById(R.id.chat_edit_text);

        MessageDummyData.initData();

        mRecyclerView=findViewById(R.id.chat_recycler_view);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        mAdapter=new ChatAdapter(this, MessageDummyData.mMessage);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(manager);
    }

    public void sendButton(View view) {
        String content=mSendText.getText().toString();
        if (!TextUtils.isEmpty(content)) {
            Message message = new Message(Message.TYPE_SEND, content);
            mAdapter.addMessage(message);
            mRecyclerView.smoothScrollToPosition(mAdapter.getItemCount()-1);
            mSendText.setText("");
        }
    }
}
