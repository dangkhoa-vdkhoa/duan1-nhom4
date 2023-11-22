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
import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.model.Product;

import java.util.ArrayList;

public class ThemGHAdapter extends RecyclerView.Adapter<ThemGHAdapter.MyViewHolder> {

    private ArrayList<Product> mList;
    private Context context;

    public ThemGHAdapter(Context context , ArrayList<Product> mList){

        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.themgdlayout , parent ,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(mList.get(position).getHinh()).into(holder.imageView);
        holder.tenFood.setText(mList.get(position).getTen());
        holder.tvGiaFood.setText(mList.get(position).getGia());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        View addGHFood;
        TextView tenFood,tvGiaFood;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.m_imageHome);
            tenFood = itemView.findViewById(R.id.tenFoodHome);
            tvGiaFood = itemView.findViewById(R.id.tvGiaFoodHome);
            addGHFood = itemView.findViewById(R.id.btnThemGHHome);

        }
    }
}