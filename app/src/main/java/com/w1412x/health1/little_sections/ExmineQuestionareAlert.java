package com.w1412x.health1.little_sections;

import static android.system.Os.close;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.w1412x.health1.NetworkRequest;
import com.w1412x.health1.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class ExmineQuestionareAlert extends RelativeLayout {

    public Button submit_button;
    public SharedPreferences  sharedPreferences;
    public TextView title_view,description_view;
    private LinearLayout question_container;
    private RelativeLayout loading_view;
    ArrayList<QuestionOption>question_views=new ArrayList<QuestionOption>();
    public ImageButton close_button;
    private JSONObject json;
    private Context context;
    private String url;
    private Handler handler;
    public ExmineQuestionareAlert(Context context,JSONObject json,String url) {
        super(context);
        this.url=url;
        this.context=context;
        this.json=json;////
        this.handler=new Handler();
        init(context);
        fill_alert();
    }

    public ExmineQuestionareAlert(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ExmineQuestionareAlert(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
    public String title;
    private void init(Context context) {
        // 加载布局文件
        loading_view=findViewById(R.id.exmine_page_loading_view);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.questionare_alert, this, true);
        description_view=findViewById(R.id.exmine_page_questionare_description_view);
        title_view=findViewById(R.id.questionnaire_title);
        submit_button=findViewById(R.id.exmine_page_questionare_submit_button);
        submit_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences=context.getSharedPreferences("user",Context.MODE_PRIVATE);
                Toast.makeText(context,ExmineQuestionareAlert.this.get_answers().toString(), Toast.LENGTH_SHORT).show();
                String id="";
                try{
                    if(ExmineQuestionareAlert.this.json.getString("state")=="Y"){//获取得到的问卷的id
                        id=ExmineQuestionareAlert.this.json.getString("id");
                    }else{//如果请求状态为E显示错误信息
                        Toast.makeText(ExmineQuestionareAlert.this.context,ExmineQuestionareAlert.this.json.getString("error_message"),Toast.LENGTH_SHORT).show();
                        return;
                    }
                }catch (Exception e){
                    Toast.makeText(ExmineQuestionareAlert.this.context,"未知错误",Toast.LENGTH_SHORT).show();
                    return;
                }
                String phone_number=sharedPreferences.getString("phone_number","");
                NetworkRequest request=new NetworkRequest();
                request.add("id",id);
                request.add("phone_number",phone_number);
                request.add("answers",ExmineQuestionareAlert.this.get_answers());
                try{
                    loading_view.setVisibility(View.VISIBLE);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            JSONObject re_json=new JSONObject();
                            try{
                                re_json=request.post(ExmineQuestionareAlert.this.url);
                            }catch (Exception e){//出现网络错误
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(ExmineQuestionareAlert.this.context,"网络错误，提交失败",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                return;
                            }
                            final JSONObject json=re_json;
                            ExmineQuestionareAlert.this.handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    int score=-1;
                                    try{
                                        if(json.getString("state")=="Y"){
                                            score=json.getInt("score");
                                        }else{//回复的状态为E
                                            Toast.makeText(ExmineQuestionareAlert.this.context,json.getString("error_messsage"),Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if(score!=-1){
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
                                    }catch (Exception e){
                                        Toast.makeText(ExmineQuestionareAlert.this.context,"未知错误",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }).start();
                }catch (Exception e){
                    loading_view.setVisibility(View.GONE);
                    Toast.makeText(ExmineQuestionareAlert.this.context,"网络错误,提交失败",Toast.LENGTH_SHORT).show();
                    close_button.callOnClick();
                }
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
    //填充问卷内容
    public void fill_alert(){
        try{
            title_view.setText(this.json.getString("title"));
            description_view.setText(this.json.getString("description"));
            JSONArray questionsArray = this.json.getJSONArray("questions");
            for (int i = 0; i < questionsArray.length(); i++) {
                JSONObject questionObject = questionsArray.getJSONObject(i);
                question_views.add(new QuestionOption(this.context,questionObject));
                add_question(question_views.get(i));
            }
        }catch (Exception e){
            Log.e("E",e.getMessage().toString());
        }
    }
    JSONArray get_answers(){
        JSONArray a=new JSONArray();
        for(int i=0;i<question_views.size();i++){
            a.put(question_views.get(i).get_answer());
        }
        return a;
    }
}
