package com.example.duan1_nhom4.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClickGioHangAdminAdapter extends RecyclerView.Adapter<ClickGioHangAdminAdapter.MyViewHolder> {
    private DatabaseReference addUser = FirebaseDatabase.getInstance().getReference("User");
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
    public void onBindViewHolder(@NonNull ClickGioHangAdminAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        User user = mList.get(position);
        holder.tensp.setText(mList.get(position).getTen());
        holder.tien.setText(mList.get(position).getGia());
        holder.sosp.setText(mList.get(position).getSosp());
        Glide.with(context).load(mList.get(position).getImg()).into(holder.img);
//        String trangthai ="";
//        if (mList.get(position).getTrangThai() == 1){
//            trangthai = "Đã Xác Nhận";
//        }else {
//            trangthai = "Chưa Xác Nhận";
//        }
        holder.xn.setText("Chưa Xác Nhận");

        int tt = Integer.parseInt((mList.get(position).getGia()))*Integer.parseInt(mList.get(position).getSosp());
        holder.tongtien.setText(String.valueOf(tt));

        holder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác Nhận Đơn Hàng");
                builder.setIcon(R.drawable.ic_sticky_note_2);
                builder.setMessage("Are you sure? ");
                Map<String, Object> updates = new HashMap<>();
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String id = user.getId();
                        int tthai = 1;
                        updates.put("trangThai",tthai);
                        addUser.child(id).updateChildren(updates)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(context, "Xác Nhận Thành Công", Toast.LENGTH_SHORT).show();
                                        mList.clear();
                                    }
                                });
                        holder.xn.setText("Đã Xác Nhận");
                        holder.xn.setTextColor(Color.GREEN);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String id = user.getId();
                        int tthai = 0;
                        updates.put("trangThai",tthai);
                        addUser.child(id).updateChildren(updates)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        //Toast.makeText(context, "Xác Nhận Thành Công", Toast.LENGTH_SHORT).show();
                                        mList.clear();
                                    }
                                });
                        holder.xn.setTextColor(Color.RED);

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

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout layout_item;
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
            layout_item = itemView.findViewById(R.id.layout_item_giohang_admin);
        }
    }
}
