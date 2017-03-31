package com.example.quoctuan.datetimepicker_quanlycongviec;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.quoctuan.datetimepicker_quanlycongviec.model.CongViec;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText editCongViec,editNoiDung;

    TextView txtNgay,txtGio;

    Button btnDate,btnTime,btnThemCongViec;

    //khai báo dataSrc lưu trữ
    ArrayList<CongViec>  arrCongViec = new ArrayList<>();
    //khai báo bộ ba cho ListView
    ArrayAdapter<CongViec> congViecAdapter  = null;
    ListView lvCongViec;
    Calendar calendar;
    Date ngay;
    Date gio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        getDefaulInfo();
        addEvents();
    }


    private void addControls() {

        txtNgay = (TextView) findViewById(R.id.txtNgay);
        txtGio = (TextView) findViewById(R.id.txtGio);

        editCongViec = (EditText) findViewById(R.id.editCongViec);
        editNoiDung = (EditText) findViewById(R.id.editNoiDung);

        btnDate = (Button) findViewById(R.id.btnDate);
        btnThemCongViec = (Button) findViewById(R.id.btnThemCongViec);
        btnTime = (Button) findViewById(R.id.btnTime);

        lvCongViec = (ListView) findViewById(R.id.lvCongViec);
        congViecAdapter = new ArrayAdapter<CongViec>(MainActivity.this,
                android.R.layout.simple_list_item_1,arrCongViec);
        lvCongViec.setAdapter(congViecAdapter);

    }


    private void addEvents() {

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hienThiNgay();
            }
        });
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hienThiGioi();
            }
        });
        btnThemCongViec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyThemCongViec();
            }
        });

    }

    private void xuLyThemCongViec() {

        String ten = editCongViec.getText()+"";
        String nd = editNoiDung.getText()+"";
        CongViec cv = new CongViec(ten,nd,ngay,gio);
        arrCongViec.add(cv);
        congViecAdapter.notifyDataSetChanged();
//        sau khi cập nhật thì reset
        editCongViec.setText("");
        editNoiDung.setText("");
        editCongViec.requestFocus();

    }

    private void hienThiGioi() {

        TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String s = hourOfDay+" : "+minute;
                int hourtam = hourOfDay;
                if (hourtam>12){
                    hourtam = hourOfDay-12;
                }
                txtGio.setText(hourOfDay+" : "+minute +(hourtam>12?"PM":"AM"));
//              lưu lại giờ
                txtGio.setTag(s);
//               lưu vết lại giờ
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                gio = calendar.getTime();
            }
        };
        String s = txtGio.getTag()+"";
        String strArr[] = s.split(":");
        int gio = Integer.parseInt(strArr[0]);
        int phut = Integer.parseInt(strArr[1]);
        TimePickerDialog time = new TimePickerDialog(MainActivity.this,
                callback,gio,phut,true);
        time.setTitle("Chọn giờ hoàn thành");
        time.show();

    }

    private void hienThiNgay() {

        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtNgay.setText(dayOfMonth+"/"+month+"/"+year);
                ngay = calendar.getTime();

            }
        };
//        xử lý ngày giờ trong datepickerdialog
        String s = txtNgay.getText()+"";
        String strArr[] = s.split("/");
        int ngay = Integer.parseInt(strArr[0]);
        int thang = Integer.parseInt(strArr[1])-1;
        int nam = Integer.parseInt(strArr[2]);
        DatePickerDialog pic = new DatePickerDialog(MainActivity.this,
                callback,nam,thang,ngay);
        pic.setTitle("Chọn ngày hoàn thành");
        pic.show();
    }

    public void getDefaulInfo(){

//        lấy ngày hệ thống
        calendar = Calendar.getInstance();
        SimpleDateFormat dft = null;
//       Định dạng ngày tháng năm
        dft = new SimpleDateFormat("dd/MM/yyy", Locale.getDefault());
        String strDate = dft.format(calendar.getTime());
//       hiển thị giao diện
        txtNgay.setText(strDate);
//       Định dạng giờ
        dft = new SimpleDateFormat("hh:mm:a",Locale.getDefault());
        String strTime = dft.format(calendar.getTime());
//        hiển thị lên giao diện
        txtGio.setText(strTime);
//      lấy giờ theo 24 để lập trình
        dft = new SimpleDateFormat("HH:mm",Locale.getDefault());
        txtGio.setTag(dft.format(calendar.getTime()));

        editCongViec.requestFocus();
//      gán calenda.getTime() cho ngày hoàn thành giờ hoàn thành
        ngay = calendar.getTime();
        gio = calendar.getTime();

    }
}
