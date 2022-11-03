package com.example.team_project.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    public static final String KEY="042e1a6b137a4fab85268f543d07c27c";
    private static  RetrofitInterface retrofitInterface;
    private static RetrofitClient instance=null;
    private final static String RETROFIT_URL = " https://open.assembly.go.kr/portal/openapi/";

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RETROFIT_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
    }
    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }
    public static RetrofitInterface getRetrofitInterface() {
        return retrofitInterface;
    }
}
