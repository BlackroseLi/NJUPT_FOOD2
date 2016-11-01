package com.example.lgggggggx.shiyanzhou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张文杰 on 2016/10/25.
 */
public class details extends Activity {
    public static final int SHOW_RESPONSE = 0;
    private List<Comment> commentList=new ArrayList<>();
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
                    Toast.makeText(details.this,response,Toast.LENGTH_LONG).show();
                    String datas[]=response.split("[#]");
                    String temp[];
                    int len=datas.length;
                    for(int i=0;i<len;i++)
                    {
                        temp=datas[i].split("[?]");
                        Log.d("length",temp.length+"");
                        Comment cmn1=new Comment(temp[0],temp[2],temp[1]);
                        commentList.add(cmn1);
                    }


            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_main);
        RatingBar bar=(RatingBar)findViewById(R.id.details_rb);
        bar.setNumStars(5);
        bar.setMax(100);
        bar.setRating((float)1.5);
        bar.setStepSize((float)0.5);
        bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(details.this,""+v*20,Toast.LENGTH_SHORT).show();
            }
        });
        Intent intent=getIntent();
        int num = intent.getIntExtra("store_id",0);
        TextView tv=(TextView)findViewById(R.id.details_tv1);
        tv.setText(num+"");
        sendRequestWithHttpURLConnection(num);
//        Comment cmn1=new Comment("Jack","nice","2012.1.1");
//        commentList.add(cmn1);
//        Comment cmn2=new Comment("Rose","bad","2012.2.1");
//        commentList.add(cmn2);
        CommentAdapter adapter=new CommentAdapter(this,R.layout.comment_view,commentList);
        ListView listView = (ListView) findViewById(R.id.lv);
        listView.setAdapter(adapter);
        ImageButton ibtn=(ImageButton)findViewById(R.id.ibtn);
        final RatingBar rb=(RatingBar)findViewById(R.id.details_rb);
        final EditText et=(EditText)findViewById(R.id.et);
        ibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float store=rb.getRating();
                String user_content=et.getText().toString();
            }
        });
    }
    private void sendRequestWithHttpURLConnection(final int sto_id) {
// 开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    OutputStream out = null;
                    URL url = new URL("http://115.28.16.78:8080/lgx/comment.jsp");
                    connection = (HttpURLConnection) url.openConnection();
                    Log.d("connection","get");
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    StringBuffer params = new StringBuffer();
                    params.append("sto_id").append("=").append("401");

                    byte[] bypes = params.toString().getBytes();

                    connection.getOutputStream().write(bypes);// 输入参数

                    InputStream in=connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new
                            InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Log.d("huifu",response.toString());
                    Message message = new Message();
                    message.what = SHOW_RESPONSE;
// 将服务器返回的结果存放到Message中
                    message.obj = response.toString();
                    handler.sendMessage(message);

                } catch (Exception e) {
                    Log.d("cuowu","exc");
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
