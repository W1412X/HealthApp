package com.w1412x.health1.little_sections;

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
public class ExmineDengerousLevelAlert extends RelativeLayout {

    public Button confirm_button;


    public ExmineDengerousLevelAlert(Context context) {
        super(context);
        init(context);
    }

    public ExmineDengerousLevelAlert(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ExmineDengerousLevelAlert(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        // 加载布局文件
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.exmine_page_dangerous_level_alert, this, true);
        confirm_button=findViewById(R.id.exmine_page_dengerous_confirm_button);
        confirm_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"确认成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
