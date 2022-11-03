package com.example.team_project.MainFragment;

import static com.example.team_project.Retrofit.RetrofitClient.KEY;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team_project.Adapter.LatestAdapter;
import com.example.team_project.Adapter.LatestDATA;
import com.example.team_project.Adapter.RowData;
import com.example.team_project.Event.LinePagerIndicatorDecoration;
import com.example.team_project.Event.SnapPagerScrollListener;
import com.example.team_project.LatestRowListActivity;
import com.example.team_project.R;
import com.example.team_project.Retrofit.RetrofitClient;
import com.example.team_project.RowDetailActivity;
import com.example.team_project.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private final String TAG=this.getClass().getSimpleName();
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView_latest;
    private LatestAdapter latestAdapter;
    private ArrayList<RowData> rowList=new ArrayList<>();
    public HomeFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentHomeBinding.inflate(inflater,container,false);
        recyclerView_latest=binding.rvLatest;
        getData();
        binding.btnDetail.setOnClickListener(this);
        return binding.getRoot();
    }
    JSONArray row;
    private void getData() {
        RetrofitClient.getRetrofitInterface().get_발의법률안(KEY,"JSON","1","20","21")
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d(TAG, "onResponse: "+response.body());
                        try {
                            JSONObject jsonObject=new JSONObject(response.body());
                            JSONArray jsonArray=new JSONArray(jsonObject.getString("nzmimeepazxkubdpn"));

                            JSONArray head=new JSONArray(jsonObject.getString("nzmimeepazxkubdpn")).getJSONObject(0).getJSONArray("head");
                            row=new JSONArray(jsonObject.getString("nzmimeepazxkubdpn")).getJSONObject(1).getJSONArray("row");
                            Log.d(TAG, "row: "+row);
                            for(int i=0;i<3;i++){
                                if (!"null".equals(row.getJSONObject(i).getString("COMMITTEE"))){
                                    rowList.add(new RowData(
                                                    row.getJSONObject(i).getString("BILL_NO"),
                                                    row.getJSONObject(i).getString("BILL_NAME"),
                                                    row.getJSONObject(i).getString("PROPOSER"),
                                                    row.getJSONObject(i).getString("PROPOSE_DT"),
                                                    row.getJSONObject(i).getString("COMMITTEE_ID"),
                                                    row.getJSONObject(i).getString("COMMITTEE"),
                                                    row.getJSONObject(i).getString("DETAIL_LINK")
                                            ));
                                }else {
                                    rowList.add(new RowData(
                                            row.getJSONObject(i).getString("BILL_NO"),
                                            row.getJSONObject(i).getString("BILL_NAME"),
                                            row.getJSONObject(i).getString("PROPOSER"),
                                            row.getJSONObject(i).getString("PROPOSE_DT"),
                                            "-",
                                            "-",
                                            row.getJSONObject(i).getString("DETAIL_LINK")
                                    ));
                                }

                            }

                            latestAdapter=new LatestAdapter(rowList);
                            recyclerView_latest.setLayoutManager(new LinearLayoutManager(
                                    getActivity(), LinearLayoutManager.HORIZONTAL, false));

                            latestAdapter.setOnItemClickListener((v, pos) -> {
                                startActivity(new Intent(getActivity(),RowDetailActivity.class).putExtra("row",rowList.get(pos)));
                            });

                            recyclerView_latest.setAdapter(latestAdapter);
                            setRecyclerViewEvent();



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

    private void setRecyclerViewEvent() {
        Log.d(TAG, "setRecyclerViewEvent: ");
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        recyclerView_latest.addItemDecoration(new LinePagerIndicatorDecoration());
        snapHelper.attachToRecyclerView(recyclerView_latest);
        SnapPagerScrollListener listener = new SnapPagerScrollListener(
                new PagerSnapHelper(),
                SnapPagerScrollListener.ON_SCROLL,
                true,
                new SnapPagerScrollListener.OnChangeListener() {
                    @Override
                    public void onSnapped(int position) {
                        //position 받아서 이벤트 처리
                        Log.d(TAG, "onSnapped: "+position);
                    }
                }
        );
        recyclerView_latest.addOnScrollListener(listener);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_detail:
                startActivity(new Intent(getActivity(), LatestRowListActivity.class).putExtra("row", String.valueOf(row)));
                break;
        }
    }
}