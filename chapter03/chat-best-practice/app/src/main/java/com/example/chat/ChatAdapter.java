package com.example.chat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chat.data.Message;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private Context mContext;
    private List<Message> mMessage;

    public ChatAdapter(Context context, List<Message> messages){
        mContext=context;
        mMessage=messages;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.list_item_chat,viewGroup,false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Message message=mMessage.get(position);
        switch (message.getType()){
            case Message.TYPE_SEND:
                holder.rightChat.setVisibility(View.VISIBLE);
                holder.leftChat.setVisibility(View.GONE);
                holder.rightMessage.setText(message.getContent());
                break;
            case Message.TYPE_RECEIVE:
                holder.leftChat.setVisibility(View.VISIBLE);
                holder.rightChat.setVisibility(View.GONE);
                holder.leftMessage.setText(message.getContent());
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (mMessage!=null)
            return mMessage.size();
        return 0;
    }

    class ChatViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout leftChat;
        private TextView leftMessage;
        private LinearLayout rightChat;
        private TextView rightMessage;

        ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            leftChat=itemView.findViewById(R.id.left_chat);
            leftMessage=itemView.findViewById(R.id.left_chat_message);
            rightChat=itemView.findViewById(R.id.right_chat);
            rightMessage=itemView.findViewById(R.id.right_chat_message);
        }
    }

    void addMessage(Message message){
        mMessage.add(message);
        this.notifyDataSetChanged();

    }
}
