package com.w1412x.health1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout ad_view;
    private RelativeLayout login_view;
    private Button skip_ad_button,scan_ad_button;
    private ImageView ad_img;
    private String ad_url="https://item.m.jd.com/product/100098497993.html?gx=RnAomTM2HUCFr8Fu0t4FDigSqHh-bA&gxd=RnAoxmQIOjzemcsRq4J_XUGZgob407A&ad_od=share&utm_source=androidapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=CopyURL";
    private Handler handler;
    private boolean if_jump=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler=new Handler();
        set_ad_img();
        init_view();
        op();
        set_skip_ad_button();
        set_scan_ad_button();
    }
    void init_view(){
        ad_view=findViewById(R.id.start_ad_view);
        login_view=findViewById(R.id.start_login_view);
    }
    void op(){
        Intent intent=new Intent(MainActivity.this,MainPageActivity.class);
        //在这里执行启动应用的逻辑
        //首先检查登陆状态
        boolean login_state=true;
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
}