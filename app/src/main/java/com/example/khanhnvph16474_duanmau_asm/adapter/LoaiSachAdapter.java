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
import com.example.khanhnvph16474_duanmau_asm.fragment.LoaiSachFragment;
import com.example.khanhnvph16474_duanmau_asm.model.LoaiSach;



import java.util.ArrayList;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {

    private Context context;
    LoaiSachFragment fragment;
    private ArrayList<LoaiSach> list;
    TextView tvMaLoai, tvTenLoai;
    ImageView imgDel;

    public LoaiSachAdapter(@NonNull Context context, LoaiSachFragment fragment, ArrayList<LoaiSach> list) {
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
            v = inflater.inflate(R.layout.loai_sach_item,null);
        }
        final LoaiSach item = list.get(position);
        if(item != null){
            tvMaLoai = v.findViewById(R.id.tvMaLoaiSach);
            tvMaLoai.setText("Mã Loại Sách: "+item.maLoai);
            tvTenLoai = v.findViewById(R.id.tvTenLoaiSach);
            tvTenLoai.setText("Tên Loại Sách: "+item.tenLoai);
            imgDel = v.findViewById(R.id.imgDeleteLS);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.xoa(String.valueOf(item.maLoai));
            }
        });
        return v;
    }
}
