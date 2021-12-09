package com.example.mvoca_p;

import android.graphics.drawable.Drawable;

public class ListViewItem {
    private Drawable icon;
    private String title;
    private String singer;

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

     public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
