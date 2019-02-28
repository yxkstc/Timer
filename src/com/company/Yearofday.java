package com.company;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Yearofday {
    public  int DayNoftheyear(int year,int month,int day){
        GregorianCalendar gc = new GregorianCalendar();
        gc.set(year,month-1,day);//月份从0开始计算
        int today = gc.get(Calendar.DAY_OF_YEAR);
        return today;

    }

    public int DayNoftheyear(){
        GregorianCalendar gc = new GregorianCalendar();
        int today = gc.get(Calendar.DAY_OF_YEAR);
        return today;
    }


    public static void main(String args[]){
        Yearofday yf=new Yearofday();
        System.out.println(yf.isMoisteningYear());
    }
    //判断是否润年
    public String isMoisteningYear(){
        String a="";
        GregorianCalendar gc = new GregorianCalendar();
        int year=gc.get(Calendar.YEAR);
        if (year%100==0){
            if (year%400==0){
                a="闰年";
            }else {
                a="平年";
        }
        }else {
            if (year%4==0){
                a="闰年";
            }else {
                a="平年";
            }
        }

        return a;
    }
}
