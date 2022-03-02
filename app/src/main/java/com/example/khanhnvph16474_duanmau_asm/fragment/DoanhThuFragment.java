package com.example.khanhnvph16474_duanmau_asm.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


import androidx.fragment.app.Fragment;

import com.example.khanhnvph16474_duanmau_asm.R;
import com.example.khanhnvph16474_duanmau_asm.dao.ThongKeDAO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DoanhThuFragment extends Fragment {

    Button btnTuNgay, btnDenNgay, btnDoanhThu;
    EditText edTuNgay, edDenNgay;
    TextView tvDoanhThu;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    int mYear, mMonth, mDay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        edTuNgay = v.findViewById(R.id.edTuNgay);
        edDenNgay = v.findViewById(R.id.edDenNgay);
        tvDoanhThu = v.findViewById(R.id.tvDoanhThu);
        btnTuNgay = v.findViewById(R.id.btnTuNgay);
        btnDenNgay = v.findViewById(R.id.btnDenNgay);
        btnDoanhThu = v.findViewById(R.id.btnDoanhThu);
        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(),
                        0, mDateTuNgay, mYear, mMonth, mDay);
                d.show();
            }
        });
        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(),
                        0, mDateDenNgay, mYear, mMonth, mDay);
                d.show();
            }
        });
        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tuNgay = edTuNgay.getText().toString();
                String denNgay = edDenNgay.getText().toString();
                ThongKeDAO thongKeDAO = new ThongKeDAO(getActivity());
                tvDoanhThu.setText("Doanh Thu: "+thongKeDAO.getDoanhThu(tuNgay, denNgay)+" VNĐ");
            }
        });
        return v;
    }
    DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            mYear = i;
            mMonth = i1;
            mDay = i2;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
            edTuNgay.setText(sdf.format(c.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            mYear = i;
            mMonth = i1;
            mDay = i2;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
            edDenNgay.setText(sdf.format(c.getTime()));
        }
    };
}
