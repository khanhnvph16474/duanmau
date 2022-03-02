package com.example.khanhnvph16474_duanmau_asm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName="PNLIB";
    static final int dbVersion=2;
    public DbHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Tạo bảng Thủ Thư
        String createTableThuThu=
                "create table ThuThu (" +
                        "maTT TEXT PRIMARY KEY, " +
                        "hoTen TEXT NOT NULL, " +
                        "matKhau TEXT NOT NULL)";
        sqLiteDatabase.execSQL(createTableThuThu);

        //Tạo bảng Thành Viên
        String createTableThanhVien=
                "create table ThanhVien (" +
                        "maTV INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "hoTen TEXT NOT NULL, " +
                        "namSinh TEXT NOT NULL)";
        sqLiteDatabase.execSQL(createTableThanhVien);

        //Tạo bảng Loại Sách
        String createTableLoaiSach=
                "create table LoaiSach (" +
                        "maLoai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "tenLoai TEXT NOT NULL)";
        sqLiteDatabase.execSQL(createTableLoaiSach);

        //Tạo bảng Sách
        String createTableSach=
                "create table Sach (" +
                        "maSach INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "tenSach TEXT NOT NULL, " +
                        "giaThue INTEGER NOT NULL," +
                        "maLoai INTEGER REFERENCES LoaiSach(maLoai))";
        sqLiteDatabase.execSQL(createTableSach);

        //Tạo bảng Phiếu Mượn
        String createTablePhieuMuon=
                "create table PhieuMuon (" +
                        "maPM INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "maTT INTEGER REFERENCES ThuThu(maTT), " +
                        "maTV INTEGER REFERENCES ThanhVien(maTV)," +
                        "maSach INTEGER REFERENCES Sach(maSach)," +
                        "tienThue INTEGER NOT NULL," +
                        "ngay DATE NOT NULL," +
                        "traSach INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(createTablePhieuMuon);


        //them mot so du lieu de test
        createTableThuThu = "INSERT INTO ThuThu VALUES('admin2', 'Nguyen van khanh','123')";
        sqLiteDatabase.execSQL(createTableThuThu);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTableThuThu = "drop table if exists ThuThu";
        sqLiteDatabase.execSQL(dropTableThuThu);
        String dropTableThanhVien = "drop table if exists ThanhVien";
        sqLiteDatabase.execSQL(dropTableThanhVien);
        String dropTableLoaiSach = "drop table if exists LoaiSach";
        sqLiteDatabase.execSQL(dropTableLoaiSach);
        String dropTableSach = "drop table if exists Sach";
        sqLiteDatabase.execSQL(dropTableSach);
        String dropTablePhieuMuon = "drop table if exists PhieuMuon";
        sqLiteDatabase.execSQL(dropTablePhieuMuon);

        onCreate(sqLiteDatabase);
    }
}
