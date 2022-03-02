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
import com.example.khanhnvph16474_duanmau_asm.dao.LoaiSachDAO;
import com.example.khanhnvph16474_duanmau_asm.fragment.SachFragment;
import com.example.khanhnvph16474_duanmau_asm.model.LoaiSach;
import com.example.khanhnvph16474_duanmau_asm.model.Sach;

import java.util.ArrayList;

public class SachAdapter extends ArrayAdapter<Sach> {
    private Context context;
    SachFragment fragment;
    private ArrayList<Sach> list;
    TextView tvMaSach, tvTenSach, tvGiaThue, tvLoai;
    ImageView imgDel;

    public SachAdapter(@NonNull Context context, SachFragment fragment, ArrayList<Sach> list) {
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
            v = inflater.inflate(R.layout.sach_item,null);
        }
        final Sach item = list.get(position);
        if(item != null){
            LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
            LoaiSach loaiSach = loaiSachDAO.getID(String.valueOf(item.maLoai));
            tvMaSach = v.findViewById(R.id.tvMaSach);
            tvMaSach.setText("Mã Sách: "+item.maSach);
            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên Sách: "+item.tenSach);
            tvGiaThue = v.findViewById(R.id.tvGiaThue);
            tvGiaThue.setText("Giá Thuê: "+item.giaThue);
            tvLoai = v.findViewById(R.id.tvLoai);
            tvLoai.setText("Loại Sách: "+loaiSach.tenLoai);
            imgDel = v.findViewById(R.id.imgDeleteS);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.xoa(String.valueOf(item.maSach));
            }
        });
        return v;
    }
}
