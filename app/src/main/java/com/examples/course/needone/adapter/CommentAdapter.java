package com.examples.course.needone.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.examples.course.needone.R;
import com.examples.course.needone.model.Comment;
import com.examples.course.needone.model.Request;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Jialu on 12/21/14.
 */
public class CommentAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Comment> commentItems;

    public CommentAdapter(Activity activity, List<Comment> commentItems) {
        this.activity = activity;
        this.commentItems = commentItems;
    }

    @Override
    public int getCount() {
        return commentItems.size();
    }

    @Override
    public Object getItem(int index) {
        return commentItems.get(index);
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
            convertView = inflater.inflate(R.layout.request_comment_list_row, null);

        Comment c =  commentItems.get(position);

        TextView user = (TextView) convertView.findViewById(R.id.comment_user);
        TextView time = (TextView) convertView.findViewById(R.id.comment_time);
        TextView commentBox = (TextView)convertView.findViewById(R.id.comment_box);

        user.setText("User: " + c.getUser());
        time.setText("Time: " + c.getTime());
        commentBox.setText("Comment: " + c.getContent());

        return convertView;
    }
}
