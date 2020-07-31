package cn.zhaoq.mycommunity.provider;

import cn.zhaoq.mycommunity.dto.AccessTokenDto;
import cn.zhaoq.mycommunity.dto.GithubUser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.springframework.stereotype.Component;

@Component
public class GithubProvider {

    public String getToken(AccessTokenDto accessTokenDto){

            MediaType mediaType = MediaType.get("application/json; charset=utf-8");

            OkHttpClient client = new OkHttpClient();

            //将对象转成json并转成RequestBody对象
            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));

            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
        try {
            Response response = client.newCall(request).execute();
            String token_tmp = response.body().string();
            String[] strsplit = token_tmp.split("&");
            String[] split = strsplit[0].split("=");

           // System.out.println(split[1]);
            return split[1];

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getGithubUser(String token){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+token)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String s = response.body().string();
            return JSON.parseObject(s,GithubUser.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
