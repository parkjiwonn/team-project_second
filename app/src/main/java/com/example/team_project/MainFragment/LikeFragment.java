package com.example.team_project.MainFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.team_project.databinding.FragmentLikeBinding;

public class LikeFragment extends Fragment {
    private final String TAG=this.getClass().getSimpleName();
    private FragmentLikeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                binding= FragmentLikeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}