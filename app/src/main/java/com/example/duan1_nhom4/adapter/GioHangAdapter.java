package com.example.duan1_nhom4.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.model.GioHang;
import com.example.duan1_nhom4.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.MyViewHolder>{
    private DatabaseReference addUser = FirebaseDatabase.getInstance().getReference("User");
    private DatabaseReference addFood = FirebaseDatabase.getInstance().getReference("GioHangDaDat");
    private DatabaseReference GhOder = FirebaseDatabase.getInstance().getReference("GioHang");

    private Context context;
    private ArrayList<GioHang> mList;
    Integer numberOder = 1;

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
    public void onBindViewHolder(@NonNull GioHangAdapter.MyViewHolder holder,final int position) {
        holder.tvTenGH.setText(mList.get(position).getTen());
        holder.tvGiaGH.setText(mList.get(position).getGia());
        holder.tvSoSP.setText(mList.get(position).getSoluong());
        Glide.with(context).load(mList.get(position).getHinh()).into(holder.ivImageGH);
        GioHang gioHang = mList.get(position);
        holder.ivMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numberOder > 1) {
                    numberOder = numberOder - 1;
                }
                holder.tvSoSP.setText(String.valueOf(numberOder));
            }
        });
        holder.ivPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOder = numberOder + 1;
                holder.tvSoSP.setText(String.valueOf(numberOder));
            }
        });

        holder.icHuy.setOnClickListener(new View.OnClickListener() {
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
                        String id = gioHang.getId();
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
        holder.btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                View v = LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.dialog_xacnhandonhang, null);
                AlertDialog dialog = builder.create();
                EditText edtTenNguoiNhan, edtSoDienThoai, edtDiaChi;
                Button btnCancel, btnXacNhanDonHang;

                edtTenNguoiNhan = v.findViewById(R.id.edtTenNguoiNhan);
                edtSoDienThoai = v.findViewById(R.id.edtSoDienThoai);
                edtDiaChi = v.findViewById(R.id.edtDiaChi);
                btnCancel = v.findViewById(R.id.btnCancel);
                btnXacNhanDonHang = v.findViewById(R.id.btnXacNhanThanhToan);
                btnXacNhanDonHang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {



                        String name = edtTenNguoiNhan.getText().toString().trim();
                        String sdt = edtSoDienThoai.getText().toString().trim();
                        String diachi = edtDiaChi.getText().toString().trim();
                        String ten = holder.tvTenGH.getText().toString().trim();
                        String gia = holder.tvGiaGH.getText().toString().trim();
                        String sosp = holder.tvSoSP.getText().toString().trim();
                        String img = gioHang.hinh;

                        User user = new User(name, sdt, diachi,ten,gia,sosp,img);

                        addUser.push().setValue(user)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(context, "Đặt hàng thành công!!!", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                });

                        String id = gioHang.getId();
                        GhOder.child(id).removeValue();
                        mList.clear();
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.setView(v);
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvTenGH,tvGiaGH,tvSoSP,icHuy;
        ImageView ivMinus,ivPlus,ivImageGH;
        Button btnThanhToan;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTenGH = itemView.findViewById(R.id.tvTenGH);
            tvGiaGH = itemView.findViewById(R.id.tvGiaGH);
            tvSoSP = itemView.findViewById(R.id.tvSoSP);
            ivMinus = itemView.findViewById(R.id.minus);
            ivPlus = itemView.findViewById(R.id.plus);
            ivImageGH = itemView.findViewById(R.id.ivImageGH);
            btnThanhToan = itemView.findViewById(R.id.btnThanhToan);
            icHuy = itemView.findViewById(R.id.tvHuy);
        }
    }
}
