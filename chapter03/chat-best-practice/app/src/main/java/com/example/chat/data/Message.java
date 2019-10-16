package com.example.chat.data;

public class Message {

    public static final int TYPE_SEND=1;
    public static final int TYPE_RECEIVE=2;

    private int Type;
    private String content;

    public Message(int type, String content) {
        Type = type;
        this.content = content;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
