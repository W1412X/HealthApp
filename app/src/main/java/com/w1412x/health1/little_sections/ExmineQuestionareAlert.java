package com.w1412x.health1.little_sections;

import static android.system.Os.close;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.w1412x.health1.R;
public class ExmineQuestionareAlert extends RelativeLayout {

    public Button submit_button;
    private LinearLayout question_container;
    public ImageButton close_button;

    public ExmineQuestionareAlert(Context context) {
        super(context);
        init(context);
    }

    public ExmineQuestionareAlert(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ExmineQuestionareAlert(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        // 加载布局文件
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.questionare_alert, this, true);
        submit_button=findViewById(R.id.exmine_page_questionare_submit_button);
        submit_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"提交成功", Toast.LENGTH_SHORT).show();
                Dialog builder=new Dialog(context);
                ExmineDengerousLevelAlert tmp=new ExmineDengerousLevelAlert(context);
                tmp.confirm_button.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.cancel();
                    }
                });
                builder.setContentView(tmp);
                builder.show();
                close_button.callOnClick();
            }
        });
        question_container=findViewById(R.id.exmine_page_questionare_question_container_linear);
        close_button=findViewById(R.id.exmine_page_questionare_close_button);
        close_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
    public void add_question(View v){
        question_container.addView(v);
    }
}
