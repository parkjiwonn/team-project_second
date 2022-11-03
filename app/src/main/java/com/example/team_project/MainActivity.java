package com.example.team_project;

import static com.example.team_project.Retrofit.RetrofitClient.KEY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.team_project.MainFragment.HomeFragment;
import com.example.team_project.MainFragment.LikeFragment;
import com.example.team_project.MainFragment.MyPageFragment;
import com.example.team_project.MainFragment.SearchFragment;
import com.example.team_project.Retrofit.RetrofitClient;
import com.example.team_project.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName(); // log
    private ActivityMainBinding binding;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        frameLayout=binding.frameLayout;

        setUI();
        RetrofitClient.getInstance();
        //get_발의법률안();
        //get_진행중입법예고();

    }

    private void get_진행중입법예고() {
        RetrofitClient.getRetrofitInterface().get_진행중입법예고(KEY,"JSON","1","2")
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d(TAG, "onResponse: "+response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d(TAG, "onFailure: "+t.getMessage());
                    }
                });
    }
    private void get_발의법률안() {
        RetrofitClient.getRetrofitInterface().get_발의법률안(KEY,"JSON","1","2","21")
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d(TAG, "response: "+response);
                        Log.d(TAG, "response.body(): "+response.body());
                        try {
                            JSONObject jsonObject=new JSONObject(response.body());
                            Log.d(TAG, "jsonObject.getString: "+jsonObject.getString("nzmimeepazxkubdpn"));
                            JSONArray jsonArray=new JSONArray(jsonObject.getString("nzmimeepazxkubdpn"));
                            Log.d(TAG, "jsonArray.getString: "+jsonArray.getString(1));
                            JSONObject jsonObject1=new JSONObject(jsonArray.getString(1));
                            Log.d(TAG, "jsonObject1: "+jsonObject1);
                            Log.d(TAG, "row: "+jsonObject1.getString("row"));
                            JSONArray jsonArray1=new JSONArray(jsonObject1.getString("row"));
                            Log.d(TAG, "jsonArray1: "+jsonArray1);
                            System.out.println();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d(TAG, "onFailure: "+t.getMessage());
                    }
                });

    }

    /**setContentView + setFragment
     * */
    private void setUI() {
        Log.d(TAG, "setUI: ");
        setContentView(binding.getRoot());
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        HomeFragment homeFragment=new HomeFragment();
        transaction.replace(R.id.frameLayout,homeFragment);
        transaction.commit();
        binding.bottomNavigationView.setOnItemSelectedListener(new TabSelectedListener());
    }
    // 하단 탭 클릭시 set 프래그 먼트
    private void setFragment(Fragment fragment) {
        Log.d(TAG, "setFragment: ");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout,fragment)
                .commit();
    }
    //하단 네비게이션 클릭 리스너
    class TabSelectedListener implements NavigationBarView.OnItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.tab_home:
                    HomeFragment homeFragment=new HomeFragment();
                    setFragment(homeFragment);
                    return true;
                case R.id.tab_search:
                    SearchFragment searchFragment=new SearchFragment();
                    setFragment(searchFragment);
                    return true;
                case R.id.tab_garden:
                    LikeFragment likeFragment =new LikeFragment();
                    setFragment(likeFragment);
                    return true;
                case R.id.tab_mypage:
                    MyPageFragment myPageFragment=new MyPageFragment();
                    setFragment(myPageFragment);
                    return true;
            }
            return false;
        }
    }

}