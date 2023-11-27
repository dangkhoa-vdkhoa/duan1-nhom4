package com.example.duan1_nhom4.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.main.ThemGHActivity;
import com.example.duan1_nhom4.model.Product;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private ArrayList<Product> mList;
    private Context context;

    public HomeAdapter(Context context , ArrayList<Product> mList){
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.homeitem , parent ,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        Product product = mList.get(position);
        myViewHolder.tenFood.setText(mList.get(position).getTen());
        myViewHolder.tvGiaFood.setText(mList.get(position).getGia());
        Glide.with(context).load(mList.get(position).getHinh()).into(holder.imageView);

        myViewHolder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(context, ThemGHActivity.class);
               intent.putExtra("chitiet",product);
               intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CardView layout_item;
        ImageView imageView;
        TextView tenFood,tvGiaFood;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_item = itemView.findViewById(R.id.layout_item);
            imageView = itemView.findViewById(R.id.m_imageHome);
            tenFood = itemView.findViewById(R.id.tenFoodHome);
            tvGiaFood = itemView.findViewById(R.id.tvGiaFoodHome);

        }
    }
}