package com.example.recycler_view.data;

public class Icon {

    private int imageResId;
    private String iconText;

    public Icon(int imageResId, String iconText) {
        this.imageResId = imageResId;
        this.iconText = iconText;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getIconText() {
        return iconText;
    }

    public void setIconText(String iconText) {
        this.iconText = iconText;
    }
}
