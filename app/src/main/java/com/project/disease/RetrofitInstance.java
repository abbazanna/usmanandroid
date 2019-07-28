package com.project.disease;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance{


    static Retrofit getRetrofit(String hostname,String port){
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .build();
        try {
            URI uri = new URI("http",null,hostname,Integer.parseInt(port),"/Server/",null,null);
            return new Retrofit.Builder().baseUrl(uri.toString()).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
