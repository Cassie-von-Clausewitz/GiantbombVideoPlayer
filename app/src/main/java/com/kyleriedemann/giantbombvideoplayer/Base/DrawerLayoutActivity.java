package com.kyleriedemann.giantbombvideoplayer.Base;

import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.kyleriedemann.giantbombvideoplayer.R;


/**
 * Created by kylealanr on 10/22/14.
 */
public abstract class DrawerLayoutActivity extends AppCompatActivity {

    public final String TAG = getLogTag();

    // navigation drawer variables
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle, mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        android.support.v7.widget.Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //make action bar icon a toggle and back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // drawer layout stuff
        mTitle = mDrawerTitle = getTitle();
        mDrawerList = (ListView) findViewById(R.id.drawer_list);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList.setOnItemClickListener(new DrawerListener());

        // initialization method, does nothing right now
        init();

        mDrawerList.setAdapter(getAdapter());

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                toolbar,
                R.string.app_name,
                R.string.app_name
        ) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if(savedInstanceState != null) {
            restoreFragment(savedInstanceState);
        } else if(savedInstanceState == null) {
            displayView(0, null);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        super.setTitle(title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // sync the state of the toggle after onRestoreInstance has occured
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // pass any config changes to the drawer
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Drawer Listener
     */
    private class DrawerListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            mDrawerLayout.closeDrawer(mDrawerList);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    displayView(position, null);
                }
            }, 300);
        }
    }

    /**
     * Method that defines what clicking on the drawer will do
     *
     * @param position
     *          The position of the clicked item
     * @param fragmentBundle
     *          A bundle that should be used to pass information to a fragment
     */
    public abstract void displayView(int position, Bundle fragmentBundle);

    /**
     * passes a bundle with information back to a fragment to resore its state
     *
     * @param savedInstanceState
     */
    public abstract void restoreFragment(Bundle savedInstanceState);

    /**
     * method used for initializations
     */
    public abstract void init();

    /**
     * Override this to change the log tag string
     * ususally just the name of the class that the log origionates from
     * @return
     *      returns the log tag for this activity
     */
    public String getLogTag() { return "Drawer Layout"; }

    /**
     * Override this to change the layout that the drawer activity uses
     * would ideally be used to change things like color
     * @return
     *      returns the layout for this activity
     */
    public int getLayout() { return R.layout.drawer_activity; }

    /**
     * Gets the drawer toggle
     *
     * @return
     *      returns the drawer toggle for this activity
     */
    public ActionBarDrawerToggle getDrawerToggle() { return mDrawerToggle; }

    /**
     * gets the list for the drawer
     * @return
     *      returns the list for this activity
     */
    public ListView getDrawerList() { return mDrawerList; }

    /**
     * gets the drawer layout
     * @return
     *      returns the drawer layout used in this activity
     */
    public DrawerLayout getDrawerLayout() { return mDrawerLayout; }

    /**
     * returns an adapter that extends base adapter if overriden
     * the adapter can then be used by the DrawerLayout
     *
     * @return
     */
    protected abstract BaseAdapter getAdapter();

    public void clearBackStack() {
        FragmentManager manager = getFragmentManager();
        // if the manager has a back stack entry, pop all entries off back stack starting with index 0
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            // if there is nothing in the back stack, let android do whatever
            super.onBackPressed();
        } else {
            // if there is something in the back stack, we need to remove it
            // the framework will handle the fragment change
            getFragmentManager().popBackStack();
            //getActionBar().setTitle(mTitle);
            //getSupportActionBar().setTitle(mTitle);
        }
    }
}
