package com.example.team_project.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team_project.R;
import com.example.team_project.databinding.ItemLastestBinding;

import java.util.ArrayList;

public class LatestAdapter extends RecyclerView.Adapter<LatestAdapter.ViewHolder>{
    ItemLastestBinding binding;
    private ArrayList<RowData> mList;
    private final String TAG=this.getClass().getSimpleName();

    public LatestAdapter(ArrayList<RowData> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding=ItemLastestBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        ViewHolder viewHolder=new ViewHolder(binding.getRoot());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.row_name.setText(mList.get(position).getBill_name());
        holder.member.setText("("+mList.get(position).getProposer()+")");
    }

    @Override
    public int getItemCount() {
        return null!=mList?mList.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView row_name;
        private TextView member;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            row_name=binding.lowName;
            //member=binding.member;
            member=itemView.findViewById(R.id.member);
            // 최신 법률안 아이템 클릭
            itemView.setOnClickListener(view -> {
                int pos=getAdapterPosition();
                if(pos!=RecyclerView.NO_POSITION){
                    if(onItemClickListener!=null){
                        onItemClickListener.OnItemClick(view,pos);
                    }
                }
            });
        }
    }
    private RowAdapter.OnItemClickListener onItemClickListener=null;
    public interface OnItemClickListener{
        void OnItemClick(View v,int pos);
    }
    public void setOnItemClickListener(RowAdapter.OnItemClickListener listener){
        this.onItemClickListener=listener;
    }
}
