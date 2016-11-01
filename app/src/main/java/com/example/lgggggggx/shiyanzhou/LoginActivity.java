package com.example.lgggggggx.shiyanzhou;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int SHOW_RESPONSE = 0;
    private Button sendRequest;
    private String user_name[];
    private EditText et1;
    private EditText et2;
    private String userid;
    private String pwd;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
// 在这里进行UI操作，将结果显示到界面上
                    user_name=response.split("[?]");
                    int flag=Integer.parseInt(user_name[1]);

                    String t=""+flag;
                    Log.d("flagget",t);
                    switch (flag){
                        case 1:

//                            TextView uer_name_tv=(TextView)findViewById(R.id.User_name);
                            String name=user_name[0];

                            Intent i=new Intent(LoginActivity.this,MainActivity.class);
                            i.putExtra("user_name",name);
                            startActivity(i);
                            Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_LONG).show();
                            finish();
                            break;
                        case 2:
                            Toast.makeText(LoginActivity.this,"登陆错误",Toast.LENGTH_LONG).show();
                            break;
                    }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sendRequest = (Button) findViewById(R.id.btn1);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        sendRequest.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn1) {
            sendRequestWithHttpURLConnection();
            Log.d("click","get");
        }
    }

    private void sendRequestWithHttpURLConnection() {
// 开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    OutputStream out = null;
                    URL url = new URL("http://115.28.16.78:8080/lgx/login.jsp");
                    connection = (HttpURLConnection) url.openConnection();
                    Log.d("connection","get");
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    StringBuffer params = new StringBuffer();

                    userid=et1.getText().toString();
                    pwd=et2.getText().toString();

                    params.append("userid").append("=").append(userid).append("&")
                            .append("password").append("=").append(pwd);

                    byte[] bypes = params.toString().getBytes();
                    Log.d("bypess", bypes.toString());
                    connection.getOutputStream().write(bypes);// 输入参数
                    Log.d("shuchu", "shuchuok");
                    InputStream in=connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new
                            InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }


                    Message message = new Message();
                    message.what = SHOW_RESPONSE;
                    // 将服务器返回的结果存放到Message中
                    message.obj = response.toString();
                    handler.sendMessage(message);


                } catch (Exception e) {

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
