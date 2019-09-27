package com.neufmer.ygfstore.util;

import android.content.Context;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.wangxing.code.mvvm.utils.ContextUtils;

/**
 * Created by WangXing on 2019/7/4.
 */
public class MapLocationUtil {

    //声明mlocationClient对象
    public AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;

    public String locationName = "";

    private static MapLocationUtil sInstance;

    public static MapLocationUtil getInstance( ) {
        if (sInstance == null) {
            sInstance = new MapLocationUtil();
        }
        return sInstance;
    }

    private MapLocationUtil() {

        mlocationClient = new AMapLocationClient(ContextUtils.getContext());
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
    }

    public void getLocation(AMapLocationListener listener) {
        mLocationOption.setOnceLocation(true);
        mLocationOption.setLocationCacheEnable(false);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.setLocationListener(listener);
        mlocationClient.startLocation();
    }

    public void stopLocation(){
        mlocationClient.stopLocation();
    }


}
