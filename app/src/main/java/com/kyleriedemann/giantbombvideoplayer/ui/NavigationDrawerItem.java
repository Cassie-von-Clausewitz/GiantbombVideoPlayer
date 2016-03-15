package com.kyleriedemann.giantbombvideoplayer.UI;

import android.graphics.drawable.Drawable;

/**
 * Created by kylealanr on 4/10/14.
 */
public class NavigationDrawerItem {

    private String title;
    private Drawable icon;

    public NavigationDrawerItem(String title, Drawable icon) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

}
