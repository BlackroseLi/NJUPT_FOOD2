package com.example.lgggggggx.shiyanzhou;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created by zzz on 2016/10/25.
 */
public class GalleryAdapter extends BaseAdapter {
    private Context context;
    private int ImageIds[];

    public GalleryAdapter(Context context, int i[]){
        this.context=context;
        this.ImageIds=i;

    }
    @Override
    public int getCount() {

        return ImageIds.length;
    }

    @Override
    public Object getItem(int position) {

        return ImageIds[position];
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView=new ImageView(context);
        imageView.setImageResource((Integer) getItem(position));
        imageView.setPadding(10,10,10,10);
        imageView.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.FILL_PARENT
                , Gallery.LayoutParams.FILL_PARENT));


        return imageView;
    }
}