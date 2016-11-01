package com.example.lgggggggx.shiyanzhou;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ListView.OnClickListener{


    private View_Update Update_Flage= View_Update.NOT_UPDATE;

    private Gallery gallery;
    private int Ids[]={R.drawable.saber,R.drawable.saber,R.drawable.saber,
            R.drawable.saber};
    private GalleryAdapter adapter;

    private ListView lv;
    private ListAdapter listAdapter;

    private static String data=new String("1$a$2$b$3$c$");
    private static String store_id[]=new String[3],store_name[]=new String[3];

    //主页面更新 处理事件
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message mg) {

            switch (mg.what){
                case 0:

                    break;
                case 1:
                    data=mg.obj.toString();
                    Toast.makeText(MainActivity.this,mg.obj.toString(),Toast.LENGTH_LONG).show();
                    Log.e("fenge",data);
                    fenge(data);
                    update_list();
                break;
                case 2:
                    data=mg.obj.toString();
                    Toast.makeText(MainActivity.this,mg.obj.toString(),Toast.LENGTH_LONG).show();
                    Log.e("fenge",data);
                    fenge(data);
                    update_list();
                    break;
                case 3:
                    data=mg.obj.toString();
                    Toast.makeText(MainActivity.this,mg.obj.toString(),Toast.LENGTH_LONG).show();
                    Log.e("fenge",data);
                    fenge(data);
                    update_list();
                    break;
                case 4:
                    data=mg.obj.toString();
                    Toast.makeText(MainActivity.this,mg.obj.toString(),Toast.LENGTH_LONG).show();
                    Log.e("fenge",data);
                    fenge(data);
                    update_list();
                    break;
                case 5:
                    data=mg.obj.toString();
                    Toast.makeText(MainActivity.this,mg.obj.toString(),Toast.LENGTH_LONG).show();
                    Log.e("fenge",data);
                    fenge(data);
                    update_list();
                    break;
                case 6:
                    break;
                case 7:
                    break;
                default:
                    break;
            }
        }
    };

    //创建主页面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fenge(data);
        update_list();
    }
    //处理网络数据
    private void fenge(String data){
        //String store[] = data.split("#"); //user[0] 为id的字符串，user[1]为name的字符串
        int j = 0, k = 0;
        String varlist[] = data.split("\\$");
        Log.e("varlist",varlist[0]+varlist[1]);
        for (int i = 0; i < varlist.length; i++) {
            if (i % 2 == 0) {
                store_id[j] = varlist[i];
                Log.e("   store_id",store_id[j]);
                j++;
            } else {
                store_name[k] = varlist[i];
                k++;
            }
        }
    }


    //根据不同标示加载list
    public void update_list(){
        lv=(ListView)findViewById(R.id.listView);
        gallery=(Gallery)findViewById(R.id.gallery);

        adapter =new GalleryAdapter(this,Ids);
        //绑定galleryAdapter
        gallery.setAdapter(adapter);

        listAdapter=new ListAdapter(MainActivity.this,store_id,store_name);
        //绑定listAdapter
        lv.setAdapter(listAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle=new Bundle();
                bundle.putInt("store_id",position);
                Intent list_intent=new Intent(MainActivity.this,ItemActivity.class);
                list_intent.putExtras(bundle);
                startActivity(list_intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //创建菜单栏
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //设置栏 点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //获取网络连接的方法
    private boolean isNetworkAvailable() {
        // 得到网络连接信息
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // 去进行判断网络是否连接
        if (manager.getActiveNetworkInfo() != null) {
            return manager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }
    //判断网络是否连接
    private boolean isOpenNetwork() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }


    // 发送信息
    private void sendRequestWithHttpURLConnection(final int mess) {
// 开启线程来发起网络请求

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    OutputStream out = null;
                    URL url = new URL("http://115.28.16.78:8080/lgx/store.jsp");
                    connection = (HttpURLConnection) url.openConnection();
                    Log.d("connection","get");
                    connection.setRequestMethod("POST");

                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);

                    StringBuffer params = new StringBuffer();
                    params.append("type").append("=").append(mess);

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
                    message.what = mess;
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

    //导航栏，导航点击事件的相应
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id==R.id.home){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = 6;
                    handler.sendMessage(message);
                }
            }).start();
        }
        else if (id == R.id.mess_1) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sendRequestWithHttpURLConnection(1);
                    Log.e("点击南一","dasd");
                }
            }).start();
        } else if (id == R.id.mess_2) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    sendRequestWithHttpURLConnection(2);
                    Log.e("点击南二","dasd");
                }
            }).start();

        } else if (id == R.id.mess_3) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sendRequestWithHttpURLConnection(3);
                }
            }).start();
        } else if (id == R.id.print_store) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sendRequestWithHttpURLConnection(5);
                }
            }).start();
        } else if (id == R.id.fruit_store) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sendRequestWithHttpURLConnection(4);
                }
            }).start();
        } else if (id == R.id.user_informatioon) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                }
            }).start();
        }else if(id==R.id.aboutus){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {

    }
}
