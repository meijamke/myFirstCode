package com.example.chat.data;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MessageDummyData extends AppCompatActivity {

    public static final List<Message> mMessage=new ArrayList<>();

    public static void initData(){
        Message message;
        message=new Message(Message.TYPE_RECEIVE,"Hi, I send a message to you, you receive it?");
        mMessage.add(message);
        message=new Message(Message.TYPE_SEND,"Yeah! Do you receive my message?");
        mMessage.add(message);
        message=new Message(Message.TYPE_RECEIVE,"Of course, let's take a chat~");
        mMessage.add(message);
    }
}
