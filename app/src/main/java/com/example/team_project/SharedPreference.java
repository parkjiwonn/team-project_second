package com.example.team_project;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.team_project.Adapter.RowData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SharedPreference {

    private final String bill_no = "BILL_NO";

    private SharedPreferences sharedPreference;
    private Context context;
    Gson gson = new GsonBuilder().create();

    public SharedPreference(Context context){
        sharedPreference = (SharedPreferences) context.getSharedPreferences("shared", 0);
        this.context = context;
    }

    public void putData(RowData rowData)
    {
        SharedPreferences.Editor editor = sharedPreference.edit();
        String data = gson.toJson(rowData);
        editor.putString(bill_no, data);
        // gson
        // key 값을 bill_no
        // value = rowdata

        editor.commit();
    }


}
