package com.example.newsfragment.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsDummyData {
    public static final List<News> mDummyNews=new ArrayList<>();

    public static void initNews(){
        for (int i=0;i<30;i++) {
            String title = getRandomLengthText("This is title "+i);
            String content = getRandomLengthText("This is content "+i);
            News news = new News(title,content);
            mDummyNews.add(news);
        }
    }

    private static String getRandomLengthText(String str){
        Random random=new Random();
        int n=random.nextInt(20)+1;
        StringBuilder stringBuilder=new StringBuilder();
        for (int i=0;i<n;i++) {
            stringBuilder.append(str);
            stringBuilder.append("    ");
        }
        return stringBuilder.toString();
    }
}
