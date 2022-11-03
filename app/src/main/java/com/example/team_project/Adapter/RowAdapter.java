package com.example.team_project.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team_project.databinding.ItemDetailBinding;

import java.util.ArrayList;

public class RowAdapter extends RecyclerView.Adapter<RowAdapter.ViewHolder>{
    private ItemDetailBinding binding;
    private ArrayList<RowData> mList;
    private final String TAG=this.getClass().getSimpleName();

    public RowAdapter(ArrayList<RowData> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding= ItemDetailBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        RowAdapter.ViewHolder viewHolder=new RowAdapter.ViewHolder(binding.getRoot());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(21<mList.get(position).getBill_name().length()){
            holder.bill_name.setText(mList.get(position).getBill_name().substring(0,21)+"...");
        }else {
            holder.bill_name.setText(mList.get(position).getBill_name());
        }
        holder.proposer.setText(mList.get(position).getProposer());
        holder.proposer_dt.setText(mList.get(position).getPropose_dt());

        if((mList.size()-1)==position){
            Log.d(TAG, "onBindViewHolder: "+mList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return null!=mList?mList.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView bill_name;
        private TextView proposer;
        private TextView proposer_dt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bill_name=binding.tvBillName;
            proposer=binding.tvProposer;
            proposer_dt=binding.tvProposerDt;
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

    private OnItemClickListener onItemClickListener=null;
    public interface OnItemClickListener{
        void OnItemClick(View v,int pos);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener=listener;
    }
}
