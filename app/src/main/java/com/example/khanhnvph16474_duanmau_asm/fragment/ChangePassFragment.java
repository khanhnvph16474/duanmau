package com.example.khanhnvph16474_duanmau_asm.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import androidx.fragment.app.Fragment;

import com.example.khanhnvph16474_duanmau_asm.R;
import com.example.khanhnvph16474_duanmau_asm.dao.ThuThuDAO;
import com.example.khanhnvph16474_duanmau_asm.model.ThuThu;
import com.google.android.material.textfield.TextInputEditText;

public class ChangePassFragment extends Fragment {
    TextInputEditText edPassOld, edPassChange, edRePassChange;
    Button btnSave, btnCancel;
    ThuThuDAO dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_change_pass, container, false);
        edPassOld = v.findViewById(R.id.edPassOld);
        edPassChange = v.findViewById(R.id.edPassChange);
        edRePassChange = v.findViewById(R.id.edRePassChange);
        btnSave = v.findViewById(R.id.SaveUserPass);
        btnCancel = v.findViewById(R.id.CancelUserPass);
        dao = new ThuThuDAO(getActivity());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edPassOld.setText("");
                edPassChange.setText("");
                edRePassChange.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("USERNAME","");
                if( validate()>0){
                    ThuThu thuThu = dao.getID(user);
                    thuThu.matKhau = edPassChange.getText().toString();
                    if(dao.updatePass(thuThu)>0){
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edPassOld.setText("");
                        edPassChange.setText("");
                        edRePassChange.setText("");
                    }else {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return v;
    }
    public int validate(){
        int check = 1;
        if(edPassOld.getText().length()==0 || edPassChange.getText().length()==0 || edRePassChange.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String PassOld = pref.getString("PASSWORD", "");
            String Pass = edPassChange.getText().toString();
            String rePass = edRePassChange.getText().toString();
            if(!PassOld.equals(edPassOld.getText().toString())){
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }if(!Pass.equals(rePass)){
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}
