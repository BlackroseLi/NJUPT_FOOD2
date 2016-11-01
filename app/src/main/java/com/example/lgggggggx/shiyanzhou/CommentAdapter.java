package com.example.lgggggggx.shiyanzhou;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */
public class CommentAdapter extends ArrayAdapter<Comment> {
    private int resourceId;
    public CommentAdapter(Context context, int textViewResourceId, List<Comment> objects){
        super(context, textViewResourceId, objects);
        resourceId=textViewResourceId;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        Comment comment = getItem(position); // 获取当前项的Comment实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView username = (TextView) view.findViewById(R.id.username);
        TextView content = (TextView) view.findViewById(R.id.content);
        TextView date= (TextView) view.findViewById(R.id.date);
        username.setText(comment.getUsername());
        content.setText(comment.getContent());
        date.setText(comment.getDate());
        return view;
    }
}
