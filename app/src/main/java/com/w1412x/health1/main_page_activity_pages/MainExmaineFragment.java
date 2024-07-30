package com.w1412x.health1.main_page_activity_pages;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.w1412x.health1.NetworkRequest;
import com.w1412x.health1.R;
import com.w1412x.health1.little_sections.ExmineDengerousLevelAlert;
import com.w1412x.health1.little_sections.ExmineQuestionareAlert;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainExmaineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainExmaineFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SharedPreferences sharedPreferences= getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RelativeLayout loading_view;
    Handler handler;
    private String examine_url="http://";

    private Button hxxt_button,xunhuanxt_button,mnxt_button,nfmxt_button,sjxt_button,ydxt_button,szxt_button,xiaohuaxt_button,gjxt_button,myxt_button;
    public MainExmaineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment exmaine.
     */
    // TODO: Rename and change types and number of parameters
    public static MainExmaineFragment newInstance(String param1, String param2) {
        MainExmaineFragment fragment = new MainExmaineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exmaine, container, false);
        init_view(view);
        return view;
    }
    void init_view(View view){
        handler=new Handler();
        //
        loading_view=view.findViewById(R.id.exmine_page_loading_view);
        //
        hxxt_button=view.findViewById(R.id.exmine_page_hxxt_button);
        hxxt_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading_view.setVisibility(View.VISIBLE);
                //请求表单内容
                NetworkRequest request=new NetworkRequest();
                request.add("phone_number",sharedPreferences.getString("phone_number",""));
                request.add("system","huxixitong");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            JSONObject json=request.post(examine_url);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Dialog builder=new Dialog(requireActivity());
                                    ExmineQuestionareAlert tmp=new ExmineQuestionareAlert(requireActivity(),json);
                                    tmp.close_button.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            builder.cancel();
                                        }
                                    });
                                    builder.setContentView(tmp);
                                    builder.show();
                                }
                            });
                        }catch (Exception e){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    loading_view.setVisibility(View.GONE);
                                    Toast.makeText(requireActivity(),"未知错误",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
        xiaohuaxt_button=view.findViewById(R.id.exmine_page_xiaohuaxt_button);
        xunhuanxt_button=view.findViewById(R.id.exmine_page_xunhuanxt_button);
        mnxt_button=view.findViewById(R.id.exmine_page_mnxt_button);
        nfmxt_button=view.findViewById(R.id.exmine_page_nfmxt_button);
        sjxt_button=view.findViewById(R.id.exmine_page_sjxt_button);
        ydxt_button=view.findViewById(R.id.exmine_page_ydxt_button);
        szxt_button=view.findViewById(R.id.exmine_page_szxt_button);
        gjxt_button=view.findViewById(R.id.exmine_page_gjxt_button);
        myxt_button=view.findViewById(R.id.exmine_page_myxt_button);
    }
}