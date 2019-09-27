package com.neufmer.ygfstore.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by WangXing on 2019/6/25.
 */
public class SelectDateBean implements Parcelable {

    private String monthDay;

    private String startDay;

    private String endDay;

    public SelectDateBean() {
    }

    private SelectDateBean(Parcel in) {
        monthDay = in.readString();
        startDay = in.readString();
        endDay = in.readString();
    }

    public static final Creator<SelectDateBean> CREATOR = new Creator<SelectDateBean>() {
        @Override
        public SelectDateBean createFromParcel(Parcel in) {
            return new SelectDateBean(in);
        }

        @Override
        public SelectDateBean[] newArray(int size) {
            return new SelectDateBean[size];
        }
    };


    public String getMonthDay() {
        return monthDay;
    }

    public void setMonthDay(String monthDay) {
        this.monthDay = monthDay;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }


    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(monthDay);
        dest.writeString(startDay);
        dest.writeString(endDay);
    }
}
