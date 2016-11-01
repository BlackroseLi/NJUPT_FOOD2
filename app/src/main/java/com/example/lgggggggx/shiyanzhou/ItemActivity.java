package com.example.lgggggggx.shiyanzhou;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Intent intent=getIntent();
        int num = intent.getIntExtra("store_id",0);
        TextView tv1=(TextView)findViewById(R.id.item_name);
        tv1.setText(num+"");
        ImageButton ibtn=(ImageButton)findViewById(R.id.item_comment);
        ibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comment_intent=new Intent(ItemActivity.this,details.class); //跳转到评论界面
                startActivity(comment_intent);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
