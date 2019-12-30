package com.bawei.week2.netutil;

import android.os.Handler;
import android.widget.ImageView;

import com.bawei.week2.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 作者：丁建飞
 * 时间：2019/12/30  14:15
 * 类名：com.bawei.week2.netutil
 */
public class NetUtil  {
    private   static  NetUtil netUtil;
    private final Handler handler;
    private final OkHttpClient okHttpClient;

    private NetUtil() {
        handler = new Handler();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();


    }

    public static NetUtil getInstance() {
        if (netUtil==null){
            synchronized (NetUtil.class){
                if (netUtil==null){
                    netUtil=new NetUtil();
                }
            }
        }
        return netUtil;
    }
    public  interface  Mycallback{
        void  getjson(String json);
        void  onError(Throwable throwable);
    }
//get请求
    public  void  getjsonGet(String http,Mycallback mycallback){
        Request request = new Request.Builder()
                .url(http)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                    mycallback.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    if (response!=null&& response.isSuccessful()){
                        String string = response.body().string();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                           mycallback.getjson(string);
                            }
                        });
                    }else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                mycallback.onError(new Exception("报错"));
                            }
                        });

                    }
            }
        });


    }



///post请求
public  void  getjsonpost(String http, Map<String ,String> map,Mycallback mycallback){
    FormBody.Builder builder= new FormBody.Builder();
    for (String key: map.keySet()){
        builder.add(key,map.get(key));
    }
    FormBody build = builder.build();


    Request request = new Request.Builder()
            .post(build)
            .url(http)
            .build();
    Call call = okHttpClient.newCall(request);
    call.enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mycallback.onError(e);
                }
            });
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response!=null&& response.isSuccessful()){
                String string = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mycallback.getjson(string);
                    }
                });
            }else {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mycallback.onError(new Exception("报错"));
                    }
                });

            }
        }
    });


}
//图片请求
public  void  getPthr(String httpimg, ImageView imageView){
    Glide.with(imageView)
            .load(httpimg)
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher_round)
            .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
            .into(imageView);

}

}
