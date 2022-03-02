package com.example.khanhnvph16474_duanmau_asm.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.khanhnvph16474_duanmau_asm.R;
import com.example.khanhnvph16474_duanmau_asm.adapter.TopAdapter;
import com.example.khanhnvph16474_duanmau_asm.dao.ThongKeDAO;
import com.example.khanhnvph16474_duanmau_asm.model.Top;

import java.util.ArrayList;

public class TopFragment extends Fragment {
    ListView lv;
    ArrayList<Top> list;
    TopAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_top, container, false);
        lv = v.findViewById(R.id.lvTop);
        ThongKeDAO thongKeDAO = new ThongKeDAO(getActivity());
        list = (ArrayList<Top>) thongKeDAO.getTop();
        adapter = new TopAdapter(getActivity(), list);
        lv.setAdapter(adapter);

        return v;
    }
}
