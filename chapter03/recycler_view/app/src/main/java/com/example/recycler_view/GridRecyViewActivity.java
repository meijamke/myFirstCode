package com.example.recycler_view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.recycler_view.data.IconDummyData;

public class GridRecyViewActivity extends AppCompatActivity implements IconAdapter.OnListItemClickListenner {

    private static final int gridCols = 3;
    private Toast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        MainActivity.initData(this);

        RecyclerView mRecyclerView = findViewById(R.id.grid_recycler_view);
        GridLayoutManager manager = new GridLayoutManager(this, gridCols);
        IconAdapter mAdapter = new IconAdapter(this, IconDummyData.mIconData, R.layout.list_item_icon_rot90, this);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onListItemClick(int position, int viewTag) {
        if (mToast != null)
            mToast.cancel();
        switch (viewTag) {
            case IconAdapter.TAG_ITEM_VIEW:
                mToast = Toast.makeText(this, "You click Item #" + position, Toast.LENGTH_LONG);
                mToast.show();
                break;
            case IconAdapter.TAG_ICON_IMAGE:
                mToast = Toast.makeText(this, "You click image #" + IconDummyData.mIconData.get(position).getIconText(), Toast.LENGTH_LONG);
                mToast.show();
                break;
            case IconAdapter.TAG_ICON_TEXT:
                mToast = Toast.makeText(this, "You click text #" + IconDummyData.mIconData.get(position).getIconText(), Toast.LENGTH_LONG);
                mToast.show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        IconDummyData.mIconData.clear();
    }
}
