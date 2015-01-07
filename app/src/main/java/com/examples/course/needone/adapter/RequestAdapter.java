package com.examples.course.needone.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.examples.course.needone.R;
import com.examples.course.needone.model.Request;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Jialu on 12/19/14.
 */
public class RequestAdapter extends BaseAdapter {

    private FragmentActivity activity;
    private LayoutInflater inflater;
    private List<Request> requestItems;
    //ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public RequestAdapter(FragmentActivity activity, List<Request> requestItems) {
        this.activity = activity;
        this.requestItems = requestItems;
    }

    @Override
    public int getCount() {
        return requestItems.size();
    }

    @Override
    public Object getItem(int index) {
        return requestItems.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.request_list_row, null);

//        if (imageLoader == null)
//            imageLoader = AppController.getInstance().getImageLoader();
//        NetworkImageView thumbNail = (NetworkImageView) convertView
//                .findViewById(R.id.thumbnail);

        //TextView subject = (TextView) convertView.findViewById(R.id.subject);
        TextView user = (TextView) convertView.findViewById(R.id.user);
        TextView location = (TextView) convertView.findViewById(R.id.location);
        TextView time = (TextView) convertView.findViewById(R.id.time);
        TextView exptime = (TextView)convertView.findViewById(R.id.exptime);
        TextView credit = (TextView) convertView.findViewById(R.id.credit);
        TextView content = (TextView) convertView.findViewById(R.id.content);

        Request m = requestItems.get(position);

//        // thumbnail image
//        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        //subject.setText("Subject: " + m.getSubject());
        user.setText("User: " + m.getUser());
        location.setText("Location: " + m.getLocation());
        time.setText("Time: " + m.getTime());
        exptime.setText("Expire time: " + m.getExptime());
        credit.setText("Credit: " + m.getCredit());
        content.setText("Content: " + m.getContent());

        return convertView;
    }
}
