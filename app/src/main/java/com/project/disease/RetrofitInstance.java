package com.project.disease;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance{


    static Retrofit getRetrofit(String hostname){
        try {
            URI uri = new URI("http",null,hostname,8084,"/Server/",null,null);
            return new Retrofit.Builder().baseUrl(uri.toString()).addConverterFactory(GsonConverterFactory.create())
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
