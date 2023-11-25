package com.example.duan1_nhom4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1_nhom4.GioHangActivity;
import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.model.GioHang;
import com.example.duan1_nhom4.model.Product;

import java.util.ArrayList;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.MyViewHolder>{

    private ArrayList<GioHang> mList;
    private Context context;

    public GioHangAdapter(Context context , ArrayList<GioHang> mList){

        this.context = context;
        this.mList = mList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_gio_hang , parent ,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangAdapter.MyViewHolder holder, int position) {
//        GioHangAdapter.MyViewHolder myViewHolder = (GioHangAdapter.MyViewHolder) holder;
//        GioHang gioHang = mList.get(position);
        holder.tvTenGH.setText(mList.get(position).getTen());
        holder.tvGiaGH.setText(mList.get(position).getGia());
        holder.tvSoSP.setText(mList.get(position).getSoluong());
        Glide.with(context).load(mList.get(position).getHinh()).into(holder.ivImageGH);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvTenGH,tvGiaGH,tvSoSP;
        ImageView ivMinus,ivPlus,ivImageGH;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTenGH = itemView.findViewById(R.id.tvTenGH);
            tvGiaGH = itemView.findViewById(R.id.tvGiaGH);
            tvSoSP = itemView.findViewById(R.id.tvSoSP);
            ivMinus = itemView.findViewById(R.id.minus);
            ivPlus = itemView.findViewById(R.id.plus);
            ivImageGH = itemView.findViewById(R.id.ivImageGH);
        }
    }
}
