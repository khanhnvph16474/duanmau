package com.example.khanhnvph16474_duanmau_asm.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.khanhnvph16474_duanmau_asm.database.DbHelper;
import com.example.khanhnvph16474_duanmau_asm.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {
    private SQLiteDatabase db;

    public ThuThuDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ThuThu obj){
        ContentValues values = new ContentValues();
        values.put("maTT", obj.maTT);
        values.put("hoTen", obj.hoTen);
        values.put("matKhau", obj.matKhau);

        return db.insert("ThuThu", null, values);
    }

    public int updatePass(ThuThu obj){
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.hoTen);
        values.put("matKhau", obj.matKhau);

        return db.update("ThuThu", values, "maTT=?", new String[]{String.valueOf(obj.maTT)});
    }

    public int delete(String id){
        return db.delete("ThuThu", "maTT=?", new String[]{id});
    }


    public List<ThuThu> getData(String sql, String...selectionArgs){

        List<ThuThu> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            ThuThu obj = new ThuThu();
            obj.maTT = c.getString(c.getColumnIndex("maTT"));
            obj.hoTen = c.getString(c.getColumnIndex("hoTen"));
            obj.matKhau = c.getString(c.getColumnIndex("matKhau"));
            list.add(obj);
        }
        return list;
    }

    public List<ThuThu> getAll(){
        String sql = "SELECT * FROM ThuThu";
        return getData(sql);
    }

    public ThuThu getID(String id){
        String sql = "SELECT * FROM ThuThu WHERE maTT=?";
        List<ThuThu> list = getData(sql, id);
        return list.get(0);
    }

    public int checkLogin(String id, String password){
        String sql = "SELECT * FROM ThuThu WHERE maTT=? AND matKhau=?";
        List<ThuThu> list = getData(sql, id, password);
        if(list.size() == 0)
            return -1;
        return 1;
    }
}
