package com.example.khanhnvph16474_duanmau_asm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khanhnvph16474_duanmau_asm.dao.ThuThuDAO;

public class LoginActivity extends AppCompatActivity {
    EditText edUserName, edPassword;
    Button btnLogin, btnCancel;
    CheckBox chkRememberPass;
    String strUser, strPass;
    ThuThuDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("ĐĂNG NHẬP");
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        btnLogin= findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
        chkRememberPass = findViewById(R.id.chkRememberPass);

        dao = new ThuThuDAO(this);

        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        edUserName.setText(pref.getString("USERNAME",""));
        edPassword.setText(pref.getString("PASSWORD",""));
        chkRememberPass.setChecked(pref.getBoolean("REMEMBER",false));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edUserName.setText("");
                edPassword.setText("");
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
    }
    public void checkLogin(){
        strUser = edUserName.getText().toString();
        strPass = edPassword.getText().toString();
        if(strUser.isEmpty()||strPass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Tên đăng nhập và mật khẩu không được bỏ trống",
                    Toast.LENGTH_SHORT).show();
        }else {
            if(dao.checkLogin(strUser, strPass)>0 || (strUser.equals("admin") && (strPass.equals("admin")))){
                Toast.makeText(getApplicationContext(),"Login thành công", Toast.LENGTH_SHORT).show();
                rememberUser(strUser,strPass,chkRememberPass.isChecked());

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("user", strUser);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(getApplicationContext(),"Tên đăng nhập và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void rememberUser(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if(!status){
            editor.clear();
        }else {
            editor.putString("USERNAME", u);
            editor.putString("PASSWORD", p);
            editor.putBoolean("REMEMBER", status);
        }
        editor.commit();
    }
}