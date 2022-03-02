package com.example.khanhnvph16474_duanmau_asm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.khanhnvph16474_duanmau_asm.dao.ThuThuDAO;
import com.example.khanhnvph16474_duanmau_asm.fragment.AddUserFragment;
import com.example.khanhnvph16474_duanmau_asm.fragment.ChangePassFragment;
import com.example.khanhnvph16474_duanmau_asm.fragment.DoanhThuFragment;
import com.example.khanhnvph16474_duanmau_asm.fragment.LoaiSachFragment;
import com.example.khanhnvph16474_duanmau_asm.fragment.PhieuMuonFragment;
import com.example.khanhnvph16474_duanmau_asm.fragment.SachFragment;
import com.example.khanhnvph16474_duanmau_asm.fragment.ThanhVienFragment;
import com.example.khanhnvph16474_duanmau_asm.fragment.TopFragment;
import com.example.khanhnvph16474_duanmau_asm.model.ThuThu;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    View mHeaderView;
    TextView tvUser;
    ThuThuDAO thuThuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        FragmentManager fragmentManager = getSupportFragmentManager();
        PhieuMuonFragment fragment = new PhieuMuonFragment();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit();

        NavigationView nv = findViewById(R.id.navigation_view);
        mHeaderView = nv.getHeaderView(0);
        tvUser = mHeaderView.findViewById(R.id.tvUser);
        Intent i = getIntent();
        String user = i.getStringExtra("user");
        tvUser.setText("Welcome " + user+" !");

        if(user.equalsIgnoreCase("admin")){
            nv.getMenu().findItem(R.id.adduser).setVisible(true);
        }

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();

                switch (item.getItemId()){
                    case R.id.phieuMuon:
                        setTitle("Quản Lý Phiếu Mượn");
                        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
                        manager.beginTransaction().replace(R.id.frame_layout, phieuMuonFragment).commit();
                        break;
                    case R.id.loaiSach:
                        setTitle("Quản Lý Loại Sách");
                        LoaiSachFragment loaiSachFragment = new LoaiSachFragment();
                        manager.beginTransaction().replace(R.id.frame_layout, loaiSachFragment).commit();
                        break;
                    case R.id.sach:
                        setTitle("Quản Lý Sách");
                        SachFragment sachFragment = new SachFragment();
                        manager.beginTransaction().replace(R.id.frame_layout, sachFragment).commit();
                        break;
                    case R.id.thanhVien:
                        setTitle("Quản Lý Thành Viên");
                        ThanhVienFragment thanhVienFragment = new ThanhVienFragment();
                        manager.beginTransaction().replace(R.id.frame_layout, thanhVienFragment).commit();
                        break;
                    case R.id.nav_top:
                        setTitle("Top 10 Sách Cho Thuê Nhiều Nhất");
                        TopFragment topFragment = new TopFragment();
                        manager.beginTransaction().replace(R.id.frame_layout, topFragment).commit();
                        break;
                    case R.id.doanhthu:
                        setTitle("Thống Kê Doanh Thu");
                        DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                        manager.beginTransaction().replace(R.id.frame_layout, doanhThuFragment).commit();
                        break;
                    case R.id.adduser:
                        setTitle("Thêm người dùng");
                        AddUserFragment addUserFragment = new AddUserFragment();
                        manager.beginTransaction().replace(R.id.frame_layout, addUserFragment).commit();
                        break;
                    case R.id.changepass:
                        setTitle("Thay Đổi Mật Khẩu");
                        ChangePassFragment changePassFragment = new ChangePassFragment();
                        manager.beginTransaction().replace(R.id.frame_layout, changePassFragment).commit();
                        break;
                    case R.id.dangXuat:
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                        break;
                }
                drawer.closeDrawers();

                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            drawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}