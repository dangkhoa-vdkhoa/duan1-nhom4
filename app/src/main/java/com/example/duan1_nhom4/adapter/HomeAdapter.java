package com.example.duan1_nhom4.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.main.ThemGHActivity;
import com.example.duan1_nhom4.model.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private ArrayList<Product> mList;
    private Context context;
    private ArrayList<Product> productList;
    private ArrayList<Product> filteredList;
    FirebaseUser currentUser;
    // Constructor và các phương thức khác

    public void filterList(ArrayList<Product> filteredList) {
        this.productList = filteredList;
        notifyDataSetChanged();
    }

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
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        myViewHolder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentUser != null) {
                    Intent intent = new Intent(context, ThemGHActivity.class);
                    intent.putExtra("chitiet", product);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }else {
                    Toast.makeText(context, "Vui lòng đăng nhập để mua hàng", Toast.LENGTH_SHORT).show();

                }
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