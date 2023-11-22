package com.example.duan1_nhom4.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        Glide.with(context).load(mList.get(position).getHinh()).into(holder.imageView);
        holder.tenFood.setText(mList.get(position).getTen());
        holder.tvGiaFood.setText(mList.get(position).getGia());
        holder.addGHFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    Product clickedProduct = mList.get(clickedPosition);

                    // Tạo Intent để chuyển đến ThemGHActivity và gửi thông tin sản phẩm
                    Intent intent = new Intent(view.getContext(), ThemGHActivity.class);
                    intent.putExtra("products", String.valueOf(clickedProduct)); // Gửi toàn bộ thông tin sản phẩm
                    view.getContext().startActivity(intent);
                }
            }
        });
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