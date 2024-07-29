package com.w1412x.health1.little_sections;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.w1412x.health1.R;
public class SelfPageMemberAddRelativeLayout extends RelativeLayout {
    private String name;
    private String state;
    private androidx.appcompat.widget.AppCompatButton name_button;
    private androidx.appcompat.widget.AppCompatButton state_button;
    private de.hdodenhof.circleimageview.CircleImageView imageView;
    public SelfPageMemberAddRelativeLayout(Context context) {
        super(context);
        init(context);
    }

    public SelfPageMemberAddRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SelfPageMemberAddRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        // 加载布局文件
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.profile_of_list_member, this, true);
        imageView=findViewById(R.id.self_page_member_list_name_button_profile);

        state_button=findViewById(R.id.self_page_member_list_state_button);
        name_button=findViewById(R.id.self_page_member_list_name_button);
        state_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}