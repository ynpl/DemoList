package com.neufmer.ygfstore.event.param;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by WangXing on 2019/7/18.
 */
public class PictureParam implements Parcelable {

    private int id;

    private List<String> imageList;

    public PictureParam() {
    }

    private PictureParam(Parcel in) {
        id = in.readInt();
        imageList = in.createStringArrayList();
    }

    public static final Creator<PictureParam> CREATOR = new Creator<PictureParam>() {
        @Override
        public PictureParam createFromParcel(Parcel in) {
            return new PictureParam(in);
        }

        @Override
        public PictureParam[] newArray(int size) {
            return new PictureParam[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeStringList(imageList);
    }
}
