package com.example.team_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.team_project.Adapter.RowData;
import com.example.team_project.databinding.ActivityRowDetailBinding;

public class RowDetailActivity extends AppCompatActivity {
    private final String TAG=this.getClass().getSimpleName();
    private ActivityRowDetailBinding binding;
    private RowData data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        binding=ActivityRowDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        data= (RowData) getIntent().getSerializableExtra("row");
        Log.d(TAG, "onCreate: "+data);
        Log.d(TAG, "onCreate: "+data.getLink_url());
        setUI();
        binding.tvContent.setOnClickListener(view -> {
            Log.d(TAG, "setOnClickListener: "+data.getLink_url());
            startActivity(new Intent(this,WebViewActivity.class).putExtra("url",data.getLink_url()));
        });
    }

    private void setUI() {
        binding.tvBillName.setText(data.getBill_name());
        binding.tvBillNo.setText(data.getBill_no());
        binding.tvProposeDt.setText(data.getPropose_dt());
        binding.tvProposer.setText(data.getProposer());
        binding.tvCurrCommittee.setText(data.getCurr_committee());
    }
}