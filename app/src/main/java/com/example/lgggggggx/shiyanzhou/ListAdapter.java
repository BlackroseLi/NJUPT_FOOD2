package com.example.lgggggggx.shiyanzhou;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/**
 * Created by zzz on 2016/10/25.
 */
public class ListAdapter extends BaseAdapter {
    private Context context;
    private String id_data[],name_data[];

    public ListAdapter(Context context,String iddata[],String namedata[]){
        this.context=context;
        this.id_data=iddata;
        this.name_data=namedata;
    }
    //获取当前数据的长度
//    @Override
//    public int getCount() {
//        return is.length;
//    }


    @Override
    public int getCount() {
        return id_data.length;
    }

    //获取当前数据的item
    @Override
    public Object getItem(int position) {
        return id_data[position];
    }
    //获取当前数据的id
    @Override
    public long getItemId(int position) {
        return position;
    }
    //当前需要加载的视图
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            //没有视图，所以加载一个
            convertView= LayoutInflater.from(context).inflate(R.layout.cell,null);
        }
        //convert.find...是因为要通过视图才可以

        // ImageView item_img=(ImageView)convertView.findViewById(R.id.cell_img);

        TextView tv=(TextView)convertView.findViewById(R.id.cell_tv);
        // tv.setText(position+":"+data[position]);
        //  tv.setText("id="+MainActivity.user_id+",name="+MainActivity.user_name);
        tv.setText("id="+id_data[position]+",name="+name_data[position]);

        return convertView;
    }
}