package com.example.team_project.MainFragment;

import static com.example.team_project.Retrofit.RetrofitNaverSearch.client_id;
import static com.example.team_project.Retrofit.RetrofitNaverSearch.client_pw;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.team_project.Retrofit.ApiInterface;
import com.example.team_project.Retrofit.RetrofitNaverSearch;
import com.example.team_project.databinding.FragmentMyPageBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPageFragment extends Fragment {
    private final String TAG=this.getClass().getSimpleName();
    private FragmentMyPageBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentMyPageBinding.inflate(inflater,container,false);

//        binding.step1.setOnClickListener(view -> {
//            searchNaverNews("발의");
//        });
//        binding.step2.setOnClickListener(view -> {
//            searchNaverNews("입법예고");
//        });
//        binding.step3.setOnClickListener(view -> {
//            searchNaverNews("법사위심사");
//        });
//        binding.step4.setOnClickListener(view -> {
//            searchNaverNews("본회의심의");
//        });
//        binding.step5.setOnClickListener(view -> {
//            searchNaverNews("법률안 이송");
//        });
        return binding.getRoot();
    }


    private void searchNaverNews(String keyword) {
        Log.d(TAG, "searchNaverNews: "+keyword);
        RetrofitNaverSearch.getInstance().create(ApiInterface.class).getSearchResult(
                client_id,client_pw,"news.json",keyword)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful() && response.body() != null){
                            String result = response.body();
                            Log.d(TAG, "성공 : " + result);
                        }else{
                            Log.e(TAG, "실패 : " + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d(TAG, "onFailure: "+t.getMessage());
                    }
                });
    }
}