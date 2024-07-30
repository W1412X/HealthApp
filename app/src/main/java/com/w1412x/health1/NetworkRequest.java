package com.w1412x.health1;
import androidx.annotation.Nullable;

import okhttp3.*;
import java.io.IOException;
import org.json.JSONObject;
public class NetworkRequest {
    private final OkHttpClient client = new OkHttpClient();
    private JSONObject json;
    public NetworkRequest(){
        json=new JSONObject();
    }
    public void add(String key,Object val){
        try{
            this.json.put(key,val);
        }catch (Exception e){

        }
    }
    // 发送GET请求
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    // 发送POST请求
    public JSONObject post(String url) throws IOException {
        RequestBody body = RequestBody.create(this.json.toString(), MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            try{
                JSONObject r=new JSONObject(response.body().string());
                return r;
            }catch (Exception e){
                return null;
            }
        }
    }
}