package com.example.recycler_view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.recycler_view.data.Icon;
import com.example.recycler_view.data.IconDummyData;

public class MainActivity extends AppCompatActivity {

    /**
     * used for linear ver
     * **/
    public static void initData(Context context) {

        Icon icon;
        //busy to insert♂ data~
        icon = new Icon(R.drawable.ic_ac_unit_black_18dp,
                context.getString(R.string.ac_unit));
        IconDummyData.setmIconData(icon);

        icon = new Icon(R.drawable.ic_airport_shuttle_black_18dp,
                context.getString(R.string.airport_shuttle));
        IconDummyData.setmIconData(icon);

        icon = new Icon(R.drawable.ic_all_inclusive_black_18dp,
                context.getString(R.string.all_inclusive));
        IconDummyData.setmIconData(icon);

        icon = new Icon(R.drawable.ic_beach_access_black_18dp,
                context.getString(R.string.beach_access));
        IconDummyData.setmIconData(icon);

        icon = new Icon(R.drawable.ic_business_center_black_18dp,
                context.getString(R.string.business_center));
        IconDummyData.setmIconData(icon);

        icon = new Icon(R.drawable.ic_casino_black_18dp,
                context.getString(R.string.casino));
        IconDummyData.setmIconData(icon);

        icon = new Icon(R.drawable.ic_child_care_black_18dp,
                context.getString(R.string.child_care));
        IconDummyData.setmIconData(icon);

        icon = new Icon(R.drawable.ic_child_friendly_black_18dp,
                context.getString(R.string.child_friendly));
        IconDummyData.setmIconData(icon);

        icon = new Icon(R.drawable.ic_fitness_center_black_18dp,
                context.getString(R.string.fitness_center));
        IconDummyData.setmIconData(icon);

        icon = new Icon(R.drawable.ic_free_breakfast_black_18dp,
                context.getString(R.string.afree_breakfast));
        IconDummyData.setmIconData(icon);
    }

    /**
     * used for linear hor, grid, staggered
     * **/
    public static void initRandomData(Context context) {

        Icon icon;
        //busy to insert♂ data~
        icon = new Icon(R.drawable.ic_ac_unit_black_18dp,
                IconDummyData.getRandomLengthText(context.getString(R.string.ac_unit)));
        IconDummyData.setmIconData(icon);

        icon = new Icon(R.drawable.ic_airport_shuttle_black_18dp,
                IconDummyData.getRandomLengthText(context.getString(R.string.airport_shuttle)));
        IconDummyData.setmIconData(icon);

        icon = new Icon(R.drawable.ic_all_inclusive_black_18dp,
                IconDummyData.getRandomLengthText(context.getString(R.string.all_inclusive)));
        IconDummyData.setmIconData(icon);

        icon = new Icon(R.drawable.ic_beach_access_black_18dp,
                IconDummyData.getRandomLengthText(context.getString(R.string.beach_access)));
        IconDummyData.setmIconData(icon);

        icon = new Icon(R.drawable.ic_business_center_black_18dp,
                IconDummyData.getRandomLengthText(context.getString(R.string.business_center)));
        IconDummyData.setmIconData(icon);

        icon = new Icon(R.drawable.ic_casino_black_18dp,
                IconDummyData.getRandomLengthText(context.getString(R.string.casino)));
        IconDummyData.setmIconData(icon);

        icon = new Icon(R.drawable.ic_child_care_black_18dp,
                IconDummyData.getRandomLengthText(context.getString(R.string.child_care)));
        IconDummyData.setmIconData(icon);

        icon = new Icon(R.drawable.ic_child_friendly_black_18dp,
                IconDummyData.getRandomLengthText(context.getString(R.string.child_friendly)));
        IconDummyData.setmIconData(icon);

        icon = new Icon(R.drawable.ic_fitness_center_black_18dp,
                IconDummyData.getRandomLengthText(context.getString(R.string.fitness_center)));
        IconDummyData.setmIconData(icon);

        icon = new Icon(R.drawable.ic_free_breakfast_black_18dp,
                IconDummyData.getRandomLengthText(context.getString(R.string.afree_breakfast)));
        IconDummyData.setmIconData(icon);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void linearVerticalRecyclerView(View view) {
        Intent intent = new Intent(MainActivity.this, LinearVerRecyViewActivity.class);
        startActivity(intent);
    }

    public void linearHorizontalRecyclerView(View view) {
        Intent intent = new Intent(MainActivity.this, LinearHorRecyViewActivity.class);
        startActivity(intent);
    }

    public void gridRecyclerView(View view) {
        Intent intent = new Intent(MainActivity.this, GridRecyViewActivity.class);
        startActivity(intent);
    }

    public void staggeredGridRecyclerView(View view) {
        Intent intent = new Intent(MainActivity.this, StaggeredRecyViewActivity.class);
        startActivity(intent);
    }
}
