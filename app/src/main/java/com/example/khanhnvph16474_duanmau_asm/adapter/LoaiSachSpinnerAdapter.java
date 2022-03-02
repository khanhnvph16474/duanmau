package com.example.khanhnvph16474_duanmau_asm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.khanhnvph16474_duanmau_asm.R;
import com.example.khanhnvph16474_duanmau_asm.model.LoaiSach;

import java.util.ArrayList;

public class LoaiSachSpinnerAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    private ArrayList<LoaiSach> list;
    TextView tvMaLoaiSach, tvTenLoaiSach;

    public LoaiSachSpinnerAdapter(@NonNull Context context, ArrayList<LoaiSach> list) {
        super(context, 0, list);
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_sach_item_spinner,null);
        }
        final LoaiSach item = list.get(position);
        if(item != null) {
            tvMaLoaiSach = v.findViewById(R.id.tvMaLoaiSachSp);
            tvMaLoaiSach.setText(item.maLoai + ". ");
            tvTenLoaiSach = v.findViewById(R.id.tvTenLoaiSachSp);
            tvTenLoaiSach.setText(item.tenLoai);
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_sach_item_spinner,null);
        }
        final LoaiSach item = list.get(position);
        if(item != null) {
            tvMaLoaiSach = v.findViewById(R.id.tvMaLoaiSachSp);
            tvMaLoaiSach.setText(item.maLoai + ". ");
            tvTenLoaiSach = v.findViewById(R.id.tvTenLoaiSachSp);
            tvTenLoaiSach.setText(item.tenLoai);
        }
        return v;
    }
}
