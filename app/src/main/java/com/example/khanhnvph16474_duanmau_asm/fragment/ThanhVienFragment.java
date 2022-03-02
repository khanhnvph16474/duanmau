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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.khanhnvph16474_duanmau_asm.R;
import com.example.khanhnvph16474_duanmau_asm.adapter.ThanhVienAdapter;
import com.example.khanhnvph16474_duanmau_asm.dao.ThanhVienDAO;
import com.example.khanhnvph16474_duanmau_asm.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ThanhVienFragment extends Fragment {
    ListView lv;
    ArrayList<ThanhVien> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaTV, edTenTV, edNamSinh;
    Button btnSave, btnCancel;

    static ThanhVienDAO dao;
    ThanhVienAdapter adapter;
    ThanhVien item;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        lv = v.findViewById(R.id.lvThanhVien);
        fab = v.findViewById(R.id.fabTV);
        dao = new ThanhVienDAO(getActivity());
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
        dialog.setContentView(R.layout.thanh_vien_dialog);
        edMaTV = dialog.findViewById(R.id.edMaTV);
        edTenTV = dialog.findViewById(R.id.edTenTV);
        edNamSinh = dialog.findViewById(R.id.edNamSinh);
        btnSave = dialog.findViewById(R.id.btnSaveTV);
        btnCancel = dialog.findViewById(R.id.btnCancelTV);

        edMaTV.setEnabled(false);
        if(type != 0){
            edMaTV.setText(String.valueOf(item.maTV));
            edTenTV.setText(item.hoTen);
            edNamSinh.setText(item.namSinh);
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
                item = new ThanhVien();
                item.hoTen = edTenTV.getText().toString();
                item.namSinh = edNamSinh.getText().toString();
                if(validate()>0){
                    if(type == 0){
                        if(dao.insert(item)>0){
                            Toast.makeText(context, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        item.maTV = Integer.parseInt(edMaTV.getText().toString());
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

    public void xoa(final String Id){
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
        list = (ArrayList<ThanhVien>) dao.getAll();
        adapter = new ThanhVienAdapter(getActivity(), this, list);
        lv.setAdapter(adapter);
    }

    public int validate(){
        int check = 1;
        if(edTenTV.getText().length()==0 || edNamSinh.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}
