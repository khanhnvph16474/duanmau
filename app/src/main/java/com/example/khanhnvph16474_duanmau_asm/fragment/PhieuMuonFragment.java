package com.example.khanhnvph16474_duanmau_asm.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.khanhnvph16474_duanmau_asm.R;
import com.example.khanhnvph16474_duanmau_asm.adapter.PhieuMuonAdapter;
import com.example.khanhnvph16474_duanmau_asm.adapter.SachSpinnerAdapter;
import com.example.khanhnvph16474_duanmau_asm.adapter.ThanhVienSpinnerAdapter;
import com.example.khanhnvph16474_duanmau_asm.dao.PhieuMuonDAO;
import com.example.khanhnvph16474_duanmau_asm.dao.SachDAO;
import com.example.khanhnvph16474_duanmau_asm.dao.ThanhVienDAO;
import com.example.khanhnvph16474_duanmau_asm.model.PhieuMuon;
import com.example.khanhnvph16474_duanmau_asm.model.Sach;
import com.example.khanhnvph16474_duanmau_asm.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



public class PhieuMuonFragment extends Fragment {
    ListView lv;
    ArrayList<PhieuMuon> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaPM;
    Spinner spTV, spSach;
    TextView tvNgay, tvTienThue;
    CheckBox chkTraSach;
    Button btnSave, btnCancel;
    static PhieuMuonDAO dao;
    PhieuMuonAdapter adapter;
    PhieuMuon item;
    ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    ArrayList<ThanhVien> listThanhVien;
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;
    int maThanhVien;
    SachSpinnerAdapter sachSpinnerAdapter;
    ArrayList<Sach> listSach;
    SachDAO sachDAO;
    Sach sach;
    Date date;
    int maSach, tienThue;

    int positionTV, positionSach;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        lv = v.findViewById(R.id.lvPhieuMuon);
        fab = v.findViewById(R.id.fabPM);
        dao = new PhieuMuonDAO(getActivity());
        capNhatLv();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(), 0);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        return v;
    }
    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        date = new Date();

        dialog.setContentView(R.layout.phieu_muon_dialog);
        edMaPM = dialog.findViewById(R.id.edMaPM);
        spTV = dialog.findViewById(R.id.spMaTV);
        spSach = dialog.findViewById(R.id.spMaSach);
        tvNgay = dialog.findViewById(R.id.tvNgay);
        tvTienThue = dialog.findViewById(R.id.tvTienThue);
        chkTraSach = dialog.findViewById(R.id.chkTraSach);
        btnCancel = dialog.findViewById(R.id.btnCancelPM);
        btnSave = dialog.findViewById(R.id.btnSavePM);
        tvNgay.setText("Ngày Thuê: " + sdf.format(date));
        thanhVienDAO = new ThanhVienDAO(context);
        listThanhVien = new ArrayList<>();
        listThanhVien = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context, listThanhVien);
        spTV.setAdapter(thanhVienSpinnerAdapter);
        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maThanhVien = listThanhVien.get(i).maTV;
                Toast.makeText(context, "Chọn " + listThanhVien.get(i).hoTen, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sachDAO = new SachDAO(context);
        listSach = new ArrayList<>();
        listSach = (ArrayList<Sach>) sachDAO.getAll();
        sachSpinnerAdapter = new SachSpinnerAdapter(context, listSach);
        spSach.setAdapter(sachSpinnerAdapter);
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maSach = listSach.get(i).maSach;
                tienThue = listSach.get(i).giaThue;
                tvTienThue.setText("Tiền Thuê: " + tienThue);
                Toast.makeText(context, "Chọn " + listSach.get(i).tenSach, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        edMaPM.setEnabled(false);
        if(type != 0){
            edMaPM.setText(String.valueOf(item.maPM));
            for (int i=0; i<listThanhVien.size(); i++)
                if(item.maTV == (listThanhVien.get(i).maTV)){
                    positionTV = i;
                }
            spTV.setSelection(positionTV);
            for (int i=0; i<listSach.size(); i++)
                if(item.maSach == (listSach.get(i).maSach)){
                    positionSach = i;
                }
            spSach.setSelection(positionSach);
            if(item.traSach==1){
                chkTraSach.setChecked(true);
            }else {
                chkTraSach.setChecked(false);
            }
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new PhieuMuon();
                item.maSach = maSach;
                item.maTV = maThanhVien;
                item.ngay = new Date();
                item.tienThue = tienThue;
                if(chkTraSach.isChecked()){
                    item.traSach = 1;
                }else {
                    item.traSach = 0;
                }
                if(validate()>0){
                    if(type==0){
                        if(dao.insert(item)>0){
                            Toast.makeText(context, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        item.maPM = Integer.parseInt(edMaPM.getText().toString());
                        if(dao.update(item)>0){
                            Toast.makeText(context, "Sửa Thành Công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Sửa Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    public void xoa(String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dao.delete(Id);
                capNhatLv();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    void capNhatLv(){
        list = (ArrayList<PhieuMuon>) dao.getAll();
        adapter = new PhieuMuonAdapter(getActivity(), this, list);
        lv.setAdapter(adapter);
    }
    public int validate(){
        int check = 1;

        return check;
    }
}
