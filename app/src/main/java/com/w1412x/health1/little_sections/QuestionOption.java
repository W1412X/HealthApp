package com.w1412x.health1.little_sections;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.w1412x.health1.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.logging.Handler;
public class QuestionOption extends RelativeLayout {
    public ArrayList<MyRadioButton>option_buttons=new ArrayList<MyRadioButton>();
    public int option_num=0;

    public TextView question_container;
    public String question="1.这是一个问题";
    public ArrayList<String>options=new ArrayList<String>();
    private Context context;
    public QuestionOption(Context context, JSONObject json) {
        super(context);
        this.context=context;
        init();
        try{
            question=json.getString("question");
            JSONArray opts=json.getJSONArray("options");
            option_num=opts.length();
            this.question_container.setText(question);
            ArrayList<MyRadioButton>button_list=new ArrayList<MyRadioButton>();
            for(int i=0;i<opts.length();i++){
                option_buttons.get(i).set_max_count(json.getInt("can_choose_count"));
                option_buttons.get(i).setText(Character.toString((char)(65+i))+". "+opts.get(i).toString());
                option_buttons.get(i).setVisibility(View.VISIBLE);
                button_list.add(option_buttons.get(i));
            }
            for(int i=0;i<button_list.size();i++){
                button_list.get(i).set_button_list(button_list);
            }
            int s;
        }catch (Exception e){
        }
    }
    public JSONArray get_answer(){
        JSONArray answers=new JSONArray();
        for(int i=0;i<option_num;i++){
            if(option_buttons.get(i).if_selected()){
                answers.put(i+1);
            }
        }
        return answers;
    };
    public QuestionOption(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuestionOption(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        // 加载布局文件
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.question_option, this, true);
        question_container=findViewById(R.id.question_options_section_title);
        MyRadioButton b1=findViewById(R.id.question_options_section_button1);
        option_buttons.add(b1);
        MyRadioButton b2=findViewById(R.id.question_options_section_button2);
        option_buttons.add(b2);
        MyRadioButton b3=findViewById(R.id.question_options_section_button3);
        option_buttons.add(b3);
        MyRadioButton b4=findViewById(R.id.question_options_section_button4);
        option_buttons.add(b4);
        MyRadioButton b5=findViewById(R.id.question_options_section_button5);
        option_buttons.add(b5);
        MyRadioButton b6=findViewById(R.id.question_options_section_button6);
        option_buttons.add(b6);
        //初始化button
    }
}

