package com.example.duan1_nhom4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom4.R;
import com.example.duan1_nhom4.model.Thongbao;

import java.util.ArrayList;

public class ThongBaoAdapter extends RecyclerView.Adapter<ThongBaoAdapter.ViewHolder> {

    private ArrayList<Thongbao> GList;
    private Context context;

    public ThongBaoAdapter(ArrayList<Thongbao> GList, Context context) {
        this.GList = GList;
        this.context = context;
    }

    @NonNull
    @Override
    public ThongBaoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_thongbao, parent ,false);
        return new ThongBaoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongBaoAdapter.ViewHolder holder, int position) {
        ViewHolder myViewHolder = (ViewHolder) holder;
//        Thongbao thongbao = GList.get(position);
        myViewHolder.TenThongBao.setText(GList.get(position).getTenthongbao());
        myViewHolder.NoiDungThongBao.setText(GList.get(position).getNoidung());
        myViewHolder.DateThongBao.setText(GList.get(position).getDatethongbao());
        myViewHolder.AvtThongBao.setText(GList.get(position).getHinh());

//        String hinh = GList.get(position).getHinh();
//
//
//        if (hinh == null || hinh.isEmpty()) {
//            // Đặt hình mặc định nếu không có hình từ Firebase
//            myViewHolder.AvtThongBao.setText("logo");
//            // Hoặc có thể đặt hình mặc định từ resources sử dụng setImageResource hoặc Glide/Picasso
//            // Ví dụ: myViewHolder.AvtThongBao.setImageResource(R.drawable.default_image);
//            // Hoặc sử dụng Glide/Picasso để tải hình từ URL mặc định
//            // Glide.with(context).load(defaultImageUrl).into(myViewHolder.AvtThongBao);
//        } else {
//
//            myViewHolder.AvtThongBao.setText(hinh);
//
//        }

    }

    @Override
    public int getItemCount() {
        return GList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//        CardView layoutThongBao;

//        TextView AvtThongBao;

        TextView TenThongBao,NoiDungThongBao,AvtThongBao,DateThongBao;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            layoutThongBao = itemView.findViewById(R.id.layoutThongBao);
            AvtThongBao = itemView.findViewById(R.id.AvtThongbao);
            TenThongBao = itemView.findViewById(R.id.TenThongBao);
            NoiDungThongBao = itemView.findViewById(R.id.NoiDungThongBao);
            DateThongBao = itemView.findViewById(R.id.DateThongBao);

        }
    }
}
