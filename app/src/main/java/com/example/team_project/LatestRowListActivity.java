package com.example.team_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.team_project.Adapter.RowAdapter;
import com.example.team_project.Adapter.RowData;
import com.example.team_project.databinding.ActivityLatestRowListBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LatestRowListActivity extends AppCompatActivity {
    private final String TAG=this.getClass().getSimpleName();
    ActivityLatestRowListBinding binding;
    private RowAdapter rowAdapter;
    private RecyclerView recyclerView;
    private ArrayList<RowData> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLatestRowListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        recyclerView=binding.rv;
        String rowStr=getIntent().getStringExtra("row");
        try {
            JSONArray jsonArray=new JSONArray(rowStr);
            Log.d(TAG, "onCreate: "+jsonArray);

            for(int i=0;i<jsonArray.length();i++){
                JSONObject element=jsonArray.getJSONObject(i);
                if(!"null".equals(element.getString("COMMITTEE"))){
                    RowData rowData=new RowData(
                            element.getString("BILL_NO"),
                            element.getString("BILL_NAME"),
                            element.getString("PROPOSER"),
                            element.getString("PROPOSE_DT"),
                            element.getString("CURR_COMMITTEE_ID"),
                            element.getString("CURR_COMMITTEE"),
                            element.getString("LINK_URL")
                    );
                    list.add(rowData);
                }else {
                    RowData rowData=new RowData(
                            element.getString("BILL_NO"),
                            element.getString("BILL_NAME"),
                            element.getString("PROPOSER"),
                            element.getString("PROPOSE_DT"),
                            "-",
                            ("-"),
                            element.getString("DETAIL_LINK")
                    );
                    list.add(rowData);
                }
            }
            setRecyclerview(list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void setRecyclerview(ArrayList<RowData> list) {
        rowAdapter=new RowAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false));
        rowAdapter.setOnItemClickListener((v, pos) -> {
            startActivity(new Intent(this,RowDetailActivity.class).putExtra("row",list.get(pos)));
        });
        recyclerView.setAdapter(rowAdapter);
    }

}
