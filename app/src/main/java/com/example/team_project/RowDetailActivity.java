package com.example.team_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;

import com.example.team_project.Adapter.RowData;
import com.example.team_project.databinding.ActivityRowDetailBinding;

// 법률안 상세보기
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
        // 즐겨찾기한 리스트가 쭉 있을 때
        binding.tvProposeDt.setText(data.getPropose_dt());
        binding.tvProposer.setText(data.getProposer());
        binding.tvCurrCommittee.setText(data.getCurr_committee());

        /**
         *  1. 최신 법률안(step =0) 선택한다면 step 1 세팅되는 것 구현
         *  2. 검색한 법룰안(step =1) 선택했을 때 step 2 or step 4 세팅되는 것 구현
         * **/

        switch (data.getStep())
        {
            case 0 : // 발의 = step 0
                binding.imageView.setImageResource(R.drawable.step1);
                break;

            case 1 : // 입법 예고 = step 1
                binding.imageView.setImageResource(R.drawable.step2);
                break;

        }

        // 북마크 상태 변화 리스너
        binding.tbBookmark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked) {
                    // 북마크가 선택됐을 때
                    // key 값이 bill_no
                    // value 값을 뭐로 넣지? row data?


                }else{
                    // 북마크 해제됐을 때
                }

            }
        });




    }
}