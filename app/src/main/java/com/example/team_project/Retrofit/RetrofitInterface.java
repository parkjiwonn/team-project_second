package com.example.team_project.Retrofit;

import static com.example.team_project.Retrofit.열린국회.발의법률안;
import static com.example.team_project.Retrofit.열린국회.법률안심사및처리_의안검색;
import static com.example.team_project.Retrofit.열린국회.진행중입법예고;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {


    @GET(법률안심사및처리_의안검색)
    Call<String> get_법률안심사및처리_의안검색(
            @Query("KEY") String KEY,
            @Query("Type") String Type,
            @Query("pIndex") String pIndex,
            @Query("pSize") String pSize,
            @Query("BILL_NAME") String BILL_NAME
    );

    @GET(법률안심사및처리_의안검색)
    Call<String> get_법률안심사및처리_의안검색(
            @Query("KEY") String KEY,
            @Query("Type") String Type,
            @Query("pIndex") String pIndex,
            @Query("pSize") String pSize
    );

    @GET(발의법률안)
    Call<String> get_발의법률안(
            @Query("KEY") String KEY,
            @Query("Type") String Type,
            @Query("pIndex") String pIndex,
            @Query("pSize") String pSize,
            @Query("age") String age
    );

    @GET(진행중입법예고)
    Call<String> get_진행중입법예고(
            @Query("KEY") String KEY,
            @Query("Type") String Type,
            @Query("pIndex") String pIndex,
            @Query("pSize") String pSize
    );
}
