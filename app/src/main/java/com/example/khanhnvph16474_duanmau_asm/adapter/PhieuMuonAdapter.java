package com.example.khanhnvph16474_duanmau_asm.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.khanhnvph16474_duanmau_asm.R;
import com.example.khanhnvph16474_duanmau_asm.dao.SachDAO;
import com.example.khanhnvph16474_duanmau_asm.dao.ThanhVienDAO;
import com.example.khanhnvph16474_duanmau_asm.fragment.PhieuMuonFragment;
import com.example.khanhnvph16474_duanmau_asm.model.PhieuMuon;
import com.example.khanhnvph16474_duanmau_asm.model.Sach;
import com.example.khanhnvph16474_duanmau_asm.model.ThanhVien;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    private Context context;
    PhieuMuonFragment fragment;
    private ArrayList<PhieuMuon> list;
    TextView tvMaPM, tvTenTV, tvTenSach, tvTienThue, tvNgay, tvTraSach;
    ImageView imgDel;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public PhieuMuonAdapter(@NonNull Context context, PhieuMuonFragment fragment, ArrayList<PhieuMuon> list) {
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
            v = inflater.inflate(R.layout.phieu_muon_item,null);
        }
        final PhieuMuon item = list.get(position);
        if(item != null) {
            tvMaPM = v.findViewById(R.id.tvMaPM);
            tvMaPM.setText("Mã Phiếu: " + item.maSach);
            sachDAO = new SachDAO(context);
            Sach sach = sachDAO.getID(String.valueOf(item.maSach));
            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên Sách: " + sach.tenSach);
            thanhVienDAO = new ThanhVienDAO(context);
            ThanhVien thanhVien = thanhVienDAO.getID(String.valueOf(item.maTV));
            tvTenTV = v.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Thành Viên: " + thanhVien.hoTen);
            tvTienThue = v.findViewById(R.id.tvTienThuePM);
            tvTienThue.setText("Tiền Thuê: " + item.tienThue);
            tvNgay = v.findViewById(R.id.tvNgayPM);
            tvNgay.setText("Ngày Thuê: " + sdf.format(item.ngay));
            tvTraSach = v.findViewById(R.id.tvTraSach);
            if(item.traSach==1){
                tvTraSach.setTextColor(Color.BLUE);
                tvTraSach.setText("Đã trả sách");
            }else {
                tvTraSach.setTextColor(Color.RED);
                tvTraSach.setText("Chưa trả sách");
            }
            imgDel = v.findViewById(R.id.imgDeletePM);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.xoa(String.valueOf(item.maPM));
            }
        });
        return v;
    }
}
