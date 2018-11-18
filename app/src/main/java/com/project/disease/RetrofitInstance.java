package com.project.disease;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance{


    static Retrofit getRetrofit(String hostname){
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
        try {
            URI uri = new URI("http",null,hostname,8084,"/Server/",null,null);
            return new Retrofit.Builder().baseUrl(uri.toString()).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
