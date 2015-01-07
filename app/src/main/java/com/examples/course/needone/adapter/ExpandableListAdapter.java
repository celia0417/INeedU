package com.examples.course.needone.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.examples.course.needone.R;
import com.examples.course.needone.model.Request;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jialu on 12/20/14.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private FragmentActivity activity;
    private List<Integer> listDataHeader;
    private HashMap<Integer, List<Request>> listDataChild;

    public ExpandableListAdapter(FragmentActivity activity, List<Integer> listDataHeader,
                                 HashMap<Integer, List<Request>> listChildData) {
        this.activity = activity;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        Request childText = (Request) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.profile_activity_child, null);
        }

        //TextView subject = (TextView) convertView.findViewById(R.id.pa_subject);
        TextView user = (TextView) convertView.findViewById(R.id.pa_user);
        TextView location = (TextView) convertView.findViewById(R.id.pa_location);
        TextView time = (TextView) convertView.findViewById(R.id.pa_time);
        TextView exptime = (TextView) convertView.findViewById(R.id.pa_exptime);
        TextView credit = (TextView) convertView.findViewById(R.id.pa_credit);
        TextView content = (TextView) convertView.findViewById(R.id.pa_content);

        //subject.setText("Subject: " + childText.getSubject());
        user.setText("Poster: " + childText.getUser());
        location.setText("Location: " + childText.getLocation());
        time.setText("Time: " + childText.getTime());
        credit.setText("Credit: " + childText.getCredit());
        content.setText("Content: " + childText.getContent());
        exptime.setText("Expire Time: " + childText.getExptime());

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Integer headerTitle = (Integer) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.profile_activity_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        switch (headerTitle) {
            case 0:
                lblListHeader.setText("MyPost");
                break;
            case 1:
                lblListHeader.setText("MyParticipation");
                break;
            case 2:
                lblListHeader.setText("MyResponse");
                break;
            default:
                lblListHeader.setText("To be filled in...");
                break;
        }


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}