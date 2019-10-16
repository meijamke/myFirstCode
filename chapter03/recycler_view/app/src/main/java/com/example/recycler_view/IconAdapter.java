package com.example.recycler_view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recycler_view.data.Icon;

import java.util.List;

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.IconViewHolder> {

    static final int TAG_ITEM_VIEW = 0;
    static final int TAG_ICON_IMAGE = 1;
    static final int TAG_ICON_TEXT = 2;
    private final OnListItemClickListenner mListItemClickListenner;
    private Context mContext;
    private List<Icon> mIcon;
    private int layoutId;

    IconAdapter(Context context, List<Icon> data, int layoutResId, OnListItemClickListenner listItemClickListenner) {
        mContext = context;
        mIcon = data;
        layoutId = layoutResId;
        mListItemClickListenner = listItemClickListenner;
    }

    @NonNull
    @Override
    public IconViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(layoutId, viewGroup, false);
        return new IconViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IconViewHolder iconViewHolder, int position) {
        Icon iconItem = mIcon.get(position);
        iconViewHolder.mIconImage.setImageResource(iconItem.getImageResId());
        iconViewHolder.mIconText.setText(iconItem.getIconText());

        iconViewHolder.itemView.setTag(TAG_ITEM_VIEW);
        iconViewHolder.mIconImage.setTag(TAG_ICON_IMAGE);
        iconViewHolder.mIconText.setTag(TAG_ICON_TEXT);
    }

    @Override
    public int getItemCount() {
        if (mIcon != null)
            return mIcon.size();
        return 0;
    }

    public interface OnListItemClickListenner {
        void onListItemClick(int position, int viewTag);
    }

    class IconViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mIconImage;
        TextView mIconText;

        IconViewHolder(View view) {
            super(view);
            mIconImage = view.findViewById(R.id.icon_image);
            mIconText = view.findViewById(R.id.icon_text);

            view.setOnClickListener(this);
            mIconImage.setOnClickListener(this);
            mIconText.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int viewTag = (int) v.getTag();
            mListItemClickListenner.onListItemClick(getAdapterPosition(), viewTag);
        }
    }


}
