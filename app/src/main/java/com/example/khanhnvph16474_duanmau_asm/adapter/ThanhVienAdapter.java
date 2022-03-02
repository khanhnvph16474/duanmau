package com.example.khanhnvph16474_duanmau_asm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.khanhnvph16474_duanmau_asm.R;
import com.example.khanhnvph16474_duanmau_asm.fragment.ThanhVienFragment;
import com.example.khanhnvph16474_duanmau_asm.model.ThanhVien;

import java.util.ArrayList;


public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    ThanhVienFragment fragment;
    private ArrayList<ThanhVien> list;
    TextView tvMaTV, tvTenTV, tvNamSinh;
    ImageView imgDel;

    public ThanhVienAdapter(@NonNull Context context, ThanhVienFragment fragment, ArrayList<ThanhVien> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanh_vien_item,null);
        }
        final ThanhVien item = list.get(position);
        if(item != null){
            tvMaTV = v.findViewById(R.id.tvMaTV);
            tvMaTV.setText("Mã Thành Viên: "+item.maTV);
            tvTenTV = v.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Tên Thành Viên: "+item.hoTen);
            tvNamSinh = v.findViewById(R.id.tvNamSinh);
            tvNamSinh.setText("Năm Sinh: "+item.namSinh);
            imgDel = v.findViewById(R.id.imgDeleteTV);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.xoa(String.valueOf(item.maTV));
            }
        });
        return v;
    }
}
