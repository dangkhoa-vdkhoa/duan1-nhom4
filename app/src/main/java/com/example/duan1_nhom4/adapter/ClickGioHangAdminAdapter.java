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
import com.example.duan1_nhom4.model.User;

import java.util.ArrayList;

public class ClickGioHangAdminAdapter extends RecyclerView.Adapter<ClickGioHangAdminAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<User> mList;

    public ClickGioHangAdminAdapter(Context context, ArrayList<User> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_click_gio_hang_admin, parent ,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ClickGioHangAdminAdapter.MyViewHolder holder, int position) {
        holder.tensp.setText(mList.get(position).getTen());
        holder.tien.setText(mList.get(position).getGia());
        holder.sosp.setText(mList.get(position).getSosp());
        Glide.with(context).load(mList.get(position).getImg()).into(holder.img);
        String trangthai ="";
        if (mList.get(position).getTrangThai() == 1){
            trangthai = "Đã Xác Nhận";
        }else {
            trangthai = "Chưa Xác Nhận";
        }
        holder.xn.setText(trangthai);

        int tt = Integer.parseInt((mList.get(position).getGia()))*Integer.parseInt(mList.get(position).getSosp());

        holder.tongtien.setText(String.valueOf(tt));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tensp,tien,xn,sosp,tongtien;
        ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tensp = itemView.findViewById(R.id.tvTenSPGioHangAdmin);
            tien = itemView.findViewById(R.id.tvTienSPGioHangAdmin);
            sosp = itemView.findViewById(R.id.tvSLSPGioHangAdmin);
            tongtien = itemView.findViewById(R.id.tvTongTienAdmin);
            img = itemView.findViewById(R.id.ivImageGioHangAdmin);
            xn = itemView.findViewById(R.id.tvXacNhanAdmin);
        }
    }
}
