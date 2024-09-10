package com.w1412x.health1.little_sections;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.w1412x.health1.R;

import org.json.JSONObject;

import java.util.ArrayList;

// MyRadioButton.java
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.AppCompatRadioButton;

public class MyRadioButton extends AppCompatRadioButton {
    private boolean if_selected = false; // 记录点击次数
    private Integer max_count;
    Handler handler;
    private ArrayList<MyRadioButton>button_list=new ArrayList<MyRadioButton>();
    Context context;
    boolean if_selected(){
        return if_selected;
    }
    public MyRadioButton(Context context,int max_count) {
        super(context);
        this.context=context;
        this.max_count=max_count;
        init();
        handler=new Handler();
    }
    public void set_button_list(ArrayList<MyRadioButton>button_list){
        this.button_list=button_list;
    }
    public MyRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        handler=new Handler();
    }
    public MyRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        handler=new Handler();
    }
    public void set_max_count(int maxCount){
        this.max_count=maxCount;
    }
    private void init() {
        // 设置自定义的Drawable作为按钮的背景
        Drawable drawable = getResources().getDrawable(R.drawable.my_radio_button);
        setButtonDrawable(drawable);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(if_selected){
                    if_selected=false;
                    setBackgroundColor(Color.parseColor("#00000000"));
                    return;
                }
                //首先判断该选项是否可以被选中
                Integer selected_count=0;
                for(int i=0;i<button_list.size();i++){//从按钮列表中获取对应的已经被选择按钮数目
                    if(button_list.get(i).if_selected()){
                       selected_count+=1;
                    }
                }
                Log.i("SELECTED_COUNT",selected_count.toString());
                if(selected_count>=MyRadioButton.this.max_count){
                }else{
                    if (if_selected) {
                        // 偶数次点击：全白色
                        if_selected=false;
                        setBackgroundColor(Color.parseColor("#00000000"));
                    } else {
                        if_selected=true;
                        // 奇数次点击：外圈白色，内圈蓝色
                        setBackgroundResource(R.drawable.my_radio_button);
                    }
                }
            }
        });
    }
}

