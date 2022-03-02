package com.example.khanhnvph16474_duanmau_asm.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.khanhnvph16474_duanmau_asm.R;
import com.example.khanhnvph16474_duanmau_asm.model.Top;

import java.util.ArrayList;

public class TopAdapter  extends ArrayAdapter<Top> {
    private Context context;
    private ArrayList<Top> list;
    TextView tvSach, tvSL;

    public TopAdapter(@NonNull Context context, ArrayList<Top> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.top_item,null);
        }
        final Top item = list.get(position);
        if(item != null){
            tvSach = v.findViewById(R.id.tvSach);
            tvSach.setText("Sách: "+item.tenSach);
            tvSL = v.findViewById(R.id.tvSL);
            tvSL.setText("Số Lượng: "+item.soLuong);
        }
        return v;
    }
}
