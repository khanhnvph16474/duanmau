package com.example.khanhnvph16474_duanmau_asm.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.khanhnvph16474_duanmau_asm.database.DbHelper;
import com.example.khanhnvph16474_duanmau_asm.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {
    private SQLiteDatabase db;

    public ThanhVienDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ThanhVien obj){
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.hoTen);
        values.put("namSinh", obj.namSinh);

        return db.insert("ThanhVien", null, values);
    }

    public int update(ThanhVien obj){
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.hoTen);
        values.put("namSinh", obj.namSinh);

        return db.update("ThanhVien", values, "maTV=?", new String[]{String.valueOf(obj.maTV)});
    }

    public int delete(String id){
        return db.delete("ThanhVien", "maTV=?", new String[]{id});
    }

    public List<ThanhVien> getAll(){
        String sql = "SELECT * FROM ThanhVien";
        return getData(sql);
    }

    public ThanhVien getID(String id){
        String sql = "SELECT * FROM ThanhVien WHERE maTV=?";
        List<ThanhVien> list = getData(sql, id);
        return list.get(0);
    }

    private List<ThanhVien> getData(String sql, String...selectionArgs){

        List<ThanhVien> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            ThanhVien obj = new ThanhVien();
            obj.maTV = Integer.parseInt(c.getString(c.getColumnIndex("maTV")));
            obj.hoTen = c.getString(c.getColumnIndex("hoTen"));
            obj.namSinh = c.getString(c.getColumnIndex("namSinh"));
            list.add(obj);
        }
        return list;
    }
}
