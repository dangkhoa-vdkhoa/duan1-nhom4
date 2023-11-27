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
import com.example.duan1_nhom4.model.DonHangDaDat;
import com.example.duan1_nhom4.model.GioHang;
import com.example.duan1_nhom4.model.User;

import java.util.ArrayList;

public class ClickGioHangAdapter extends RecyclerView.Adapter<ClickGioHangAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<User> mList;

    public ClickGioHangAdapter(Context context, ArrayList<User> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_click_gio_hang, parent ,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ClickGioHangAdapter.MyViewHolder holder, int position) {
        Integer tiensp = Integer.valueOf(holder.tien.getText().toString());
        Integer soluong = Integer.valueOf(holder.sosp.getText().toString());
        holder.tensp.setText(mList.get(position).getTen());
        holder.tien.setText(mList.get(position).getGia());
        holder.sosp.setText(mList.get(position).getSosp());
        holder.xn.setText("Chưa Xác Nhận");
        holder.tongtien.setText(tiensp*soluong);
        Glide.with(context).load(mList.get(position).getImg()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tensp,tien,xn,sosp,tongtien;
        ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);



            tensp = itemView.findViewById(R.id.tvTenSPGioHang);
            tien = itemView.findViewById(R.id.tvTienSPGioHang);
            sosp = itemView.findViewById(R.id.tvSLSPGioHang);
            tongtien = itemView.findViewById(R.id.tvTongTien);
            img = itemView.findViewById(R.id.ivImageGioHang);
        }
    }
}
