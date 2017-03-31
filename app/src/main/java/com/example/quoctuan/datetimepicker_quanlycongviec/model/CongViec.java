package com.example.quoctuan.datetimepicker_quanlycongviec.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Tuấn on 3/31/2017.
 */

public class CongViec {
    private String ten;
    private String nd;
    private Date ngay;
    private Date Gio;

    public String getNd() {
        return nd;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public Date getGio() {
        return Gio;
    }

    public void setGio(Date gio) {
        Gio = gio;
    }

    public CongViec(String ten,String nd, Date ngay, Date gio) {
        this.ten = ten;
        this.nd = nd;
        this.ngay = ngay;
        Gio = gio;
    }

    public CongViec() {
    }

//    lấy định dạng ngày

    public String getDateFormat(Date d){
        SimpleDateFormat dft = new
                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dft.format(d);
    }
//    lấy định dạng giờ
    public  String getHourFormat(Date d){
        SimpleDateFormat dft = new
                SimpleDateFormat("hh:mm:a",Locale.getDefault());
        return dft.format(d);
    }

    @Override
    public String toString() {
        return this.ten+" --- "+
                getDateFormat(this.ngay)+" --- "+
                getHourFormat(this.Gio);
    }
}
