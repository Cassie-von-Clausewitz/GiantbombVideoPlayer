package com.kyleriedemann.giantbombvideoplayer;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.BaseAdapter;

import com.kyleriedemann.giantbombvideoplayer.Authentication.AuthenticationFragment;
import com.kyleriedemann.giantbombvideoplayer.Base.BaseFragment;
import com.kyleriedemann.giantbombvideoplayer.Base.DrawerLayoutActivity;
import com.kyleriedemann.giantbombvideoplayer.Base.NavigationDrawerAdapter;
import com.kyleriedemann.giantbombvideoplayer.Base.NavigationDrawerItem;
import com.kyleriedemann.giantbombvideoplayer.VideoList.VideoListFragment;

import java.util.ArrayList;
import java.util.HashMap;

//import com.kyleriedemann.giantbombvideoplayer.Authentication.AuthenticationFragment;

public class MainActivity extends DrawerLayoutActivity {
    private static final String TAG_ACTIVE_FRAGMENT = "fragment_active";

    // constants that represent the fragments
    public static final int VIDEO = 0;
    public static final int AUTH = 1;
    private BaseFragment activeFragment = null;

    // more nav drawer stuff
    private NavigationDrawerAdapter mNavDrawerAdapter;
    private ArrayList<NavigationDrawerItem> navigationDrawerItems;
    private String[] navMenuTitles;
    private HashMap<Integer, String> fragmentTitles;
    private Bundle currentBundle;

    @Override
    public void init() {
        // retrieve array from XML
        TypedArray navigationIcons = getResources().obtainTypedArray(R.array.navigation_drawer_icons);
        navMenuTitles = getResources().getStringArray(R.array.navigation_drawer_items);
        navigationDrawerItems = new ArrayList<NavigationDrawerItem>();

        // should add items to the ArrayList of NavigationDrawerItems4
        for(int i = 0; i < navMenuTitles.length; i++) {
            // populate the navigation drawer array
            navigationDrawerItems.add(new NavigationDrawerItem(navMenuTitles[i], navigationIcons.getDrawable(i)));
        }
        // recycle the typed array when done with it
        navigationIcons.recycle();

        mNavDrawerAdapter = new NavigationDrawerAdapter(this, navigationDrawerItems);

        // we need a HashMap to map the Titles of a fragment that are outside the nav drawer
        //fragmentTitles = new HashMap<Integer, String>();
        //ex:
        //fragmentTitles.put(NEW_FRAGMENT, getString(R.string.string_id));
    }

    @Override
    public void restoreFragment(Bundle savedInstanceState) {
        //restore instance of the fragment
        activeFragment = (BaseFragment) getFragmentManager().getFragment(savedInstanceState, "activeFragment");
    }

    @Override
    public void displayView(int position, Bundle fragmentBundle) {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (position) {
            case VIDEO:
                activeFragment = new VideoListFragment();
                clearBackStack();
                break;
            case AUTH:
                activeFragment = new AuthenticationFragment();
                clearBackStack();
                break;
            default:
                break;
        }

        if(activeFragment != null) {
            if(fragmentBundle != null) {
                currentBundle = fragmentBundle;
                activeFragment.setArguments(fragmentBundle);
            }

            fragmentTransaction.setCustomAnimations(R.animator.alpha_in, R.animator.alpha_out, // animations for fragment in
                    R.animator.alpha_in, R.animator.alpha_out) // animations for fragment out
                    .replace(R.id.fragment_container, activeFragment, TAG_ACTIVE_FRAGMENT).commit(); // the replace what is inside the FrameLayout with our activeFragment

            // update the selected item and title
            if(position >= 0) {
                getDrawerList().setItemChecked(position, true); // we set the item click in the drawer to active
                getDrawerList().setSelection(position);
                setTitle(navMenuTitles[position]); // change the title of the action bar to the fragment
            } else {
                if(fragmentBundle == null) {
                    setTitle(fragmentTitles.get(position)); // same as above
                } else {
                    setTitle("Check In"); // fall back to default fragment name
                }
            }
        }
    }

    /**
     * override to change log tag
     */
    @Override
    public String getLogTag() { return "MainActivity"; }

    @Override
    protected BaseAdapter getAdapter() { return mNavDrawerAdapter; }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (activeFragment.isAdded()) {
            getFragmentManager().putFragment(outState, "activeFragment", activeFragment);
        }
    }
}
