package com.example.team_project.MainFragment;


import static com.example.team_project.Retrofit.RetrofitClient.KEY;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.team_project.R;
import com.example.team_project.Retrofit.RetrofitClient;
import com.example.team_project.SearchResultActivity;
import com.example.team_project.databinding.FragmentSearchBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment implements View.OnClickListener {
    private final String TAG=this.getClass().getSimpleName();
    private FragmentSearchBinding binding;
    private Bundle bundle=new Bundle();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentSearchBinding.inflate(inflater,container,false);
        binding.btnSearch.setOnClickListener(this);
        binding.keyword1.setOnClickListener(this);
        binding.keyword2.setOnClickListener(this);
        binding.keyword3.setOnClickListener(this);
        binding.keyword4.setOnClickListener(this);
        binding.keyword5.setOnClickListener(this);
        binding.keyword6.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: "+binding.editText.getText().toString());
        switch (view.getId()){
            case R.id.btn_search:
                bundle.putString("keyword",binding.editText.getText().toString());
                break;
            case R.id.keyword_1:
                bundle.putString("keyword",binding.keyword1.getText().toString());
                break;
            case R.id.keyword_2:
                bundle.putString("keyword",binding.keyword2.getText().toString());
                break;
            case R.id.keyword_3:
                bundle.putString("keyword",binding.keyword3.getText().toString());
                break;
            case R.id.keyword_4:
                bundle.putString("keyword",binding.keyword4.getText().toString());
                break;
            case R.id.keyword_5:
                bundle.putString("keyword",binding.keyword5.getText().toString());
                break;
            case R.id.keyword_6:
                bundle.putString("keyword",binding.keyword6.getText().toString());
                break;
        }
        startActivity(new Intent(getActivity(), SearchResultActivity.class).putExtras(bundle));
    }


}