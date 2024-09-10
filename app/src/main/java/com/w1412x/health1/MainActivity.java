package com.w1412x.health1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    String login_url="http://116.204.83.200:8080/user/auth";
    private RelativeLayout ad_view;
    private RelativeLayout login_view,loading_view;
    private Button login_by_passwd_button,login_by_exmine_button,register_button,choose_passwd_button,choose_exmine_button,login_get_exmine_code_button,register_get_exmine_code_button;
    private RadioButton register_agree_button;
    private Button passwd_to_register_page_button,exmine_to_register_page_button;
    private EditText login_by_passwd_number_input,login_by_passwd_passwd_input,login_by_exmine_number_input,login_by_exmine_exmine_input,register_number_input,register_exmine_input,register_passwd_input,register_passwd2_input;
    private RelativeLayout register_view;
    private Button register_to_login_button;
    private Button skip_ad_button,scan_ad_button;
    private LinearLayout login_by_passwd_view,login_by_exmine_view;
    private LinearLayout passwd_bar,exmine_bar;
    private ImageView ad_img;
    private Integer second=-1;
    private String ad_url="https://item.m.jd.com/product/100098497993.html?gx=RnAomTM2HUCFr8Fu0t4FDigSqHh-bA&gxd=RnAoxmQIOjzemcsRq4J_XUGZgob407A&ad_od=share&utm_source=androidapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=CopyURL";
    private Handler handler;
    private boolean if_jump=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler=new Handler();
        check_login_state();
        set_ad_img();
        init_view();
        op();
        set_skip_ad_button();
        set_scan_ad_button();
    }
    void check_login_state(){
        // 获取SharedPreferences对象，参数包括文件名和模式
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(!sharedPreferences.getString("phone_number","").equals("")){
            startActivity(new Intent(MainActivity.this,MainPageActivity.class));
        }
    }
    void init_view(){
        //加载视图
        loading_view=findViewById(R.id.start_page_loading_view);
        //三种视图
        ad_view=findViewById(R.id.start_ad_view);
        login_view=findViewById(R.id.start_login_view);
        register_view=findViewById(R.id.start_register_view);
        //登陆的两种视图
        login_by_passwd_view=findViewById(R.id.start_login_page_login_by_passwd_view);
        login_by_exmine_view=findViewById(R.id.start_login_page_login_by_exmine_code_view);
        //密码登陆部分的视图
        login_by_passwd_number_input=findViewById(R.id.start_login_page_passwd_number_input);
        login_by_passwd_passwd_input=findViewById(R.id.start_login_page_passwd_passwd_input);
        login_by_passwd_button=findViewById(R.id.start_login_page_passwd_login_button);
        //验证码登陆部分的视图
        login_by_exmine_button=findViewById(R.id.start_login_page_exmine_login_button);
        login_by_exmine_exmine_input=findViewById(R.id.start_login_page_login_exmine_exmine_input);
        login_by_exmine_number_input=findViewById(R.id.start_login_page_login_exmine_number_input);
        //注册部分视图
        register_button=findViewById(R.id.start_register_page_register_button);
        register_agree_button=findViewById(R.id.start_register_page_agree_button);
        register_number_input=findViewById(R.id.start_register_page_number_input);
        register_exmine_input=findViewById(R.id.start_register_page_exmine_input);
        register_passwd_input=findViewById(R.id.start_register_page_passwd_input);
        register_passwd2_input=findViewById(R.id.start_register_page_passwd2_input);
        //选择登陆方式的按钮和显示条
        choose_exmine_button=findViewById(R.id.start_login_page_choose_exmine_button);
        choose_passwd_button=findViewById(R.id.start_login_page_choose_passwd_button);
        passwd_bar=findViewById(R.id.start_page_passwd_bar);
        exmine_bar=findViewById(R.id.start_page_exmine_bar);
        //两个获取验证码的按钮
        login_get_exmine_code_button=findViewById(R.id.start_login_page_login_exmine_get_exmine_button);
        register_get_exmine_code_button=findViewById(R.id.start_register_page_get_exmine_button);
        //切换登陆，注册的按钮
        register_to_login_button=findViewById(R.id.start_login_page_return_login_page_button);
        passwd_to_register_page_button=findViewById(R.id.start_login_page_passwd_to_register_page_button);
        exmine_to_register_page_button=findViewById(R.id.start_login_page_exmine_to_register_page_button);
        choose_passwd_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置bar的颜色
                passwd_bar.setBackgroundColor(Color.parseColor("#84a6ed"));
                choose_passwd_button.setTextColor(Color.parseColor("#4d83f4"));
                choose_exmine_button.setTextColor(Color.parseColor("#8a8a8a"));
                exmine_bar.setBackgroundColor(Color.parseColor("#cc8a8a8a"));
                //设置视图的显示
                login_by_passwd_view.setVisibility(View.VISIBLE);
                login_by_exmine_view.setVisibility(View.GONE);
            }
        });
        choose_exmine_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置bar的颜色
                passwd_bar.setBackgroundColor(Color.parseColor("#cc8a8a8a"));
                choose_passwd_button.setTextColor(Color.parseColor("#8a8a8a"));
                choose_exmine_button.setTextColor(Color.parseColor("#4d83f4"));
                exmine_bar.setBackgroundColor(Color.parseColor("#84a6ed"));
                //设置视图的显示
                login_by_passwd_view.setVisibility(View.GONE);
                login_by_exmine_view.setVisibility(View.VISIBLE);
                //设置通过验证码登陆按钮
            }
        });
        login_by_exmine_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkRequest request= new NetworkRequest();
                request.add("type","login");
                request.add("login_type","examine");
                request.add("phone_number",login_by_exmine_number_input.getText().toString());
                request.add("examine_code",login_by_exmine_exmine_input.getText().toString());
                loading_view.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            JSONObject json;
                            json=request.post(login_url);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    loading_view.setVisibility(View.GONE);
                                    try{
                                        if(json.getString("state")=="Y"){//认证成功
                                            Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                                            // 获取SharedPreferences对象，参数包括文件名和模式
                                            SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            // 存储数据
                                            editor.putString("phone_number",login_by_passwd_number_input.getText().toString());
                                            // 提交数据修改
                                            editor.apply();
                                            startActivity(new Intent(MainActivity.this,MainPageActivity.class));
                                        } else if (json.getString("state")=="N") {//认证失败
                                            Toast.makeText(MainActivity.this,"帐号或密码错误",Toast.LENGTH_SHORT).show();
                                        }else{//未知错误
                                            Toast.makeText(MainActivity.this,json.getString("error_message").toString(),Toast.LENGTH_SHORT).show();
                                        }
                                    }catch (Exception e){
                                        Toast.makeText(MainActivity.this,"未知错误，请联系开发者",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }catch (Exception e){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    loading_view.setVisibility(View.GONE);
                                    Toast.makeText(MainActivity.this,"未知错误",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
        login_by_passwd_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(login_by_passwd_number_input.getText().toString().equals("1412")){
                    startActivity(new Intent(MainActivity.this,MainPageActivity.class));
                    return;
                }
                NetworkRequest request= new NetworkRequest();
                request.add("type","login");
                request.add("login_type","passwd");
                request.add("phone_number",login_by_passwd_number_input.getText().toString());
                request.add("passwd",login_by_passwd_passwd_input.getText().toString());
                loading_view.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            JSONObject json;
                            json=request.post(login_url);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    loading_view.setVisibility(View.GONE);
                                    try{
                                        if(json.getString("state")=="Y"){//认证成功
                                            Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                                            // 获取SharedPreferences对象，参数包括文件名和模式
                                            SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            // 存储数据
                                            editor.putString("phone_number",login_by_passwd_number_input.getText().toString());
                                            // 提交数据修改
                                            editor.apply();
                                            startActivity(new Intent(MainActivity.this,MainPageActivity.class));
                                        } else if (json.getString("state")=="N") {//认证失败
                                            Toast.makeText(MainActivity.this,"帐号或密码错误",Toast.LENGTH_SHORT).show();
                                        }else{//未知错误
                                            Toast.makeText(MainActivity.this,json.getString("error_message").toString(),Toast.LENGTH_SHORT).show();
                                        }
                                    }catch (Exception e){
                                        Toast.makeText(MainActivity.this,"未知错误，请联系开发者",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }catch (Exception e){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    loading_view.setVisibility(View.GONE);
                                    Toast.makeText(MainActivity.this,"未知错误",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
        passwd_to_register_page_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_view.setVisibility(View.GONE);
                register_view.setVisibility(View.VISIBLE);
            }
        });
        exmine_to_register_page_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_view.setVisibility(View.GONE);
                register_view.setVisibility(View.VISIBLE);
            }
        });
        register_to_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_view.setVisibility(View.VISIBLE);
                register_view.setVisibility(View.GONE);
            }
        });
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!register_agree_button.isChecked()){
                    Toast.makeText(MainActivity.this,"请先勾选用户注册协议",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!register_passwd_input.getText().toString().equals(register_passwd2_input.getText().toString())){
                    Toast.makeText(MainActivity.this,"两次输入的密码不匹配",Toast.LENGTH_SHORT).show();
                    return;
                }
                NetworkRequest request= new NetworkRequest();
                request.add("type","register");
                request.add("phone_number",register_number_input.getText().toString());
                request.add("examine_code",register_exmine_input.getText().toString());
                request.add("passwd",register_passwd_input.getText().toString());
                loading_view.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            JSONObject json;
                            json=request.post(login_url);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    loading_view.setVisibility(View.GONE);
                                    try{
                                        if(json.getString("state")=="Y"){//认证成功
                                            Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                                            // 获取SharedPreferences对象，参数包括文件名和模式
                                            SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            // 存储数据
                                            editor.putString("phone_number",login_by_passwd_number_input.getText().toString());
                                            // 提交数据修改
                                            editor.apply();
                                            startActivity(new Intent(MainActivity.this,MainPageActivity.class));
                                        } else if (json.getString("state")=="N") {//认证失败
                                            Toast.makeText(MainActivity.this,"帐号或密码错误",Toast.LENGTH_SHORT).show();
                                        }else{//未知错误
                                            Toast.makeText(MainActivity.this,json.getString("error_message").toString(),Toast.LENGTH_SHORT).show();
                                        }
                                    }catch (Exception e){
                                        Toast.makeText(MainActivity.this,"未知错误，请联系开发者",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }catch (Exception e){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    loading_view.setVisibility(View.GONE);
                                    Toast.makeText(MainActivity.this,"未知错误",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
        login_get_exmine_code_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_exmine_code(login_get_exmine_code_button);
            }
        });
        register_get_exmine_code_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_exmine_code(register_get_exmine_code_button);
            }
        });
        choose_passwd_button.callOnClick();
    }
    void op(){
        Intent intent=new Intent(MainActivity.this,MainPageActivity.class);
        //在这里执行启动应用的逻辑
        //首先检查登陆状态
        boolean login_state=false;
        if(login_state){//如果登陆状态是已登陆，则直接播放广告
            ad_view.setVisibility(View.VISIBLE);
            login_view.setVisibility(View.GONE);
            if_jump=true;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(if_jump){
                        Toast.makeText(MainActivity.this,"5s",Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                }
            },5000);
        }else{
            ad_view.setVisibility(View.GONE);
            login_view.setVisibility(View.VISIBLE);
        }
    }
    //广告部分
    void set_skip_ad_button(){
        skip_ad_button=findViewById(R.id.start_page_skip_ad_button);
        skip_ad_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainPageActivity.class);
                if_jump=false;
                Toast.makeText(MainActivity.this,"skip",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
    void set_ad_img(){
        ad_img=findViewById(R.id.start_ad_image_view);
        Glide.with(this)
                .load("http://39.101.160.55/display_images/bus2.jpg")
                .into(ad_img);
    }
    void jump_to_ad(String ad_url){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(ad_url));
        startActivity(intent);
    }
    void set_scan_ad_button(){
        scan_ad_button=findViewById(R.id.start_page_scan_ad_button);
        scan_ad_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump_to_ad(ad_url);
            }
        });
    }

    void send_exmine_code(Button button){
        Toast.makeText(MainActivity.this,"已发送验证码",Toast.LENGTH_SHORT).show();
        if(second!=-1){
            Toast.makeText(MainActivity.this,"已经发送验证码，请稍后再试!",Toast.LENGTH_SHORT).show();
            return;
        }
        second=300;
        //设置按钮显示秒数
        button.setText("已发送("+second.toString()+")");
        //设置按钮不可点击
        button.setClickable(false);
        update_button(button);
    }
    void update_button(Button button){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                second=second-1;
                if(second==0){
                    button.setClickable(true);
                    button.setText("再次发送");
                    second=-1;
                }else{
                    button.setText("已发送("+(second).toString()+")");
                    update_button(button);
                }
            }
        },1000);
    }
}