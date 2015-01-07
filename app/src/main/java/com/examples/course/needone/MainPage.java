package com.examples.course.needone;

import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.examples.course.needone.tools.GPSTracker;
import com.examples.course.needone.tools.Global;

import java.util.List;

/**
 * Created by Jialu on 12/16/14.
 */
public class MainPage extends FragmentActivity {
    private FragmentTabHost mTabHost = null;
    //private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GPSTracker gpsTracker = new GPSTracker(this);
        if (gpsTracker.canGetLocation())
        {
            Global.latitude = gpsTracker.getLatitude();
            Global.longitude = gpsTracker.getLongitude();
            System.out.println(Global.latitude);
            System.out.println(Global.longitude);
        }
        setContentView(R.layout.mainpage);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        /**
         * Define Tab Change Listener event.
         * This is invoked when tab is changed.
         */
        FragmentTabHost.OnTabChangeListener tabChangeListener = new FragmentTabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {
                android.support.v4.app.FragmentManager fragmentManager =   getSupportFragmentManager();
                android.support.v4.app.Fragment requestListFragment = (android.support.v4.app.Fragment)fragmentManager.findFragmentByTag("tab_requestList");
                android.support.v4.app.Fragment newPostFragment = (android.support.v4.app.Fragment)fragmentManager.findFragmentByTag("tab_newPost");
                android.support.v4.app.Fragment profileFragment = (android.support.v4.app.Fragment)fragmentManager.findFragmentByTag("tab_profile");
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Detaches the fragment if exists
                if(requestListFragment != null)
                    fragmentTransaction.detach(requestListFragment);
                    android.util.Log.d("mark", "============requestList detached========");
                if(newPostFragment != null)
                    fragmentTransaction.detach(newPostFragment);
//                if(messageFragment != null)
//                    fragmentTransaction.detach(messageFragment);
                if(profileFragment != null)
                    fragmentTransaction.detach(profileFragment);

                // If current tab is android
                if(tabId.equalsIgnoreCase("tab_requestList")) {

                    if(requestListFragment == null) {
                        // Create NewPost fragment and adding to fragment transaction
                        fragmentTransaction.add(R.id.realtabcontent, new RequestList(), "tab_requestList");
                        android.util.Log.d("mark", "============requestList added========");
                    }
                    else {
                        // Bring to the front, if already exists in the fragment transaction
                        fragmentTransaction.attach(requestListFragment);
                        android.util.Log.d("mark", "============requestList attached========");
                    }

                } else if(tabId.equalsIgnoreCase("tab_newPost")) {

                    if(newPostFragment == null) {
                        fragmentTransaction.add(R.id.realtabcontent, new NewPost(), "tab_newPost");
                        android.util.Log.d("mark","=========new post added=======");
                    }
                    else {
                        fragmentTransaction.attach(newPostFragment);
                    }

//                } else if(tabId.equalsIgnoreCase("tab_message")) {
//
//                    if(messageFragment == null) {
//                        fragmentTransaction.add(R.id.realtabcontent, new Message(), "tab_message");
//                    }
//                    else {
//                        fragmentTransaction.attach(messageFragment);
//                    }

                } else if(tabId.equalsIgnoreCase("tab_profile")) {

                    if(profileFragment == null) {
                        fragmentTransaction.add(R.id.realtabcontent, new Profile(), "tab_profile");
                    }
                    else {
                        fragmentTransaction.attach(profileFragment);
                    }
                }

                fragmentTransaction.commit();
            }
        };

        // Setting tabchangelistener for the tab
        mTabHost.setOnTabChangedListener(tabChangeListener);

        View requestTabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_requestlist_icon, null, false);
        ImageView requestIcon = (ImageView) requestTabIndicator.findViewById(R.id.request_icon);
        Drawable myreqIcon = getResources().getDrawable( R.drawable.ic_action_communication_textsms);
        requestIcon.setImageDrawable(myreqIcon);
        requestIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);

        View postTabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_newpost_icon, null, false);
        ImageView postIcon = (ImageView) postTabIndicator.findViewById(R.id.post_icon);
        Drawable mypostIcon = getResources().getDrawable( R.drawable.ic_action_maps_local_offer );
        postIcon.setImageDrawable(mypostIcon);
        postIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);

        View profileTabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_profile_icon, null, false);
        ImageView profileIcon = (ImageView) profileTabIndicator.findViewById(R.id.profile_icon);
        Drawable myprofileIcon = getResources().getDrawable( R.drawable.ic_action_social_person );
        profileIcon.setImageDrawable(myprofileIcon);
        profileIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);

        TabHost.TabSpec tabSpecRequestList = mTabHost
                .newTabSpec("tab_requestList")
                .setIndicator(requestTabIndicator);

        TabHost.TabSpec tabSpecNewPost = mTabHost
                .newTabSpec("tab_newPost")
                .setIndicator(postTabIndicator);

        TabHost.TabSpec tabSpecProfile = mTabHost
                .newTabSpec("tab_profile")
                .setIndicator(profileTabIndicator);

        // add tabs
        mTabHost.addTab(tabSpecRequestList, RequestList.class, null);
        mTabHost.addTab(tabSpecNewPost, NewPost.class, null);
       // mTabHost.addTab(tabSpecMessage, Message.class, null);
        mTabHost.addTab(tabSpecProfile, Profile.class, null);

       mTabHost.setCurrentTab(0);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        mTabHost = null;
    }
}
