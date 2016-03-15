package com.kyleriedemann.giantbombvideoplayer.UI;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyleriedemann.giantbombvideoplayer.R;

import java.util.ArrayList;

/**
 * Created by kylealanr on 4/10/14.
 */
public class NavigationDrawerAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NavigationDrawerItem> navigationDrawerItems;

    // default constructor
    public NavigationDrawerAdapter(){
    }

    /**
     *
     * @param context
     *      The context in which the NavDrawer is being constructed
     * @param navigationDrawerItems
     *      The ArrayList containing the DrawerItems for the Adapter
     *
     */
    public NavigationDrawerAdapter(Context context, ArrayList<NavigationDrawerItem> navigationDrawerItems){
        this.context = context;
        this.navigationDrawerItems = navigationDrawerItems;
    }

    public Context getContext() { return context; }

    public void setContext(Context context) { this.context = context; }

    public ArrayList<NavigationDrawerItem> getNavigationDrawerItems() { return navigationDrawerItems; }

    public void setNavigationDrawerItems(ArrayList<NavigationDrawerItem> navigationDrawerItems) {
        this.navigationDrawerItems = navigationDrawerItems;
    }

    /**
     * Used by the framework
     *
     * @return
     *      The number of elements in this adapter
     */
    @Override
    public int getCount() {
        return navigationDrawerItems.size();
    }

    /**
     * Used by onItemClickListener
     *
     * @param position
     *      Poition of clicked item
     * @return
     *      The object in that position
     */
    @Override
    public Object getItem(int position) {
        return navigationDrawerItems.get(position);
    }

    /**
     *
     * @param position
     *      Position of the item
     * @return
     *      The id of the item at that postiion
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * responsible for inflating and creating the View for each of the items
     * To get different behavior for the different items in a List you can do so on getView
     * EX:
     * if(position % 2 == 0) {
     *     convertView.setBackgroundColor(Color.BLUE);
     * } else {
     *     convertView.setBackgroundColor(Color.RED);
     * }
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater myInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = myInflater.inflate(R.layout.drawer_item, null);
        }
        //create the image icon and title
        ImageView iconImage = (ImageView) convertView.findViewById(R.id.drawer_icon);
        TextView title = (TextView) convertView.findViewById(R.id.drawer_text);
        //apply the specified properties to them
        iconImage.setImageDrawable(navigationDrawerItems.get(position).getIcon());
        title.setText(navigationDrawerItems.get(position).getTitle());
        //return the created view
        return convertView;
    }
}
