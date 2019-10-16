package com.example.recycler_view.data;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IconDummyData {

    public static List<Icon> mIconData = new ArrayList<>();

    public static void setmIconData(Icon icon) {
        IconDummyData.mIconData.add(icon);
    }

    public static String getRandomLengthText(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int n = random.nextInt(20) + 1;
        for (int i = 0; i < n; i++) {
            stringBuilder.append(text);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }
}
