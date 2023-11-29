package com.example.duan1_nhom4.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.model.Product;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private DatabaseReference GhOder = FirebaseDatabase.getInstance().getReference("products");
    private ArrayList<Product> mList;
    private Context context;

    public MyAdapter(Context context , ArrayList<Product> mList){

        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item , parent ,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(mList.get(position).getHinh()).into(holder.imageView);
        holder.tenFood.setText(mList.get(position).getTen());
        holder.tvGiaFood.setText(mList.get(position).getGia());
        Product product = mList.get(position);
        holder.vSPAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");
                builder.setIcon(R.drawable.ic_warning);
                builder.setMessage("Bạn có chắc chắn muốn xoá khỏi giỏ hàng '" +
                        mList.get(holder.getAdapterPosition()).getTen() + "' không?");

                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String id = product.getId();
                        GhOder.child(id).removeValue();
                        mList.clear();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        View vSPAdmin;
        TextView tenFood,tvGiaFood;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.m_image);
            tenFood = itemView.findViewById(R.id.tenFood);
            tvGiaFood = itemView.findViewById(R.id.tvGiaFood);
            vSPAdmin = itemView.findViewById(R.id.vSPAdmin);
        }
    }
}