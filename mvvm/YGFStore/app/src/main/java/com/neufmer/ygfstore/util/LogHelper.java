package com.neufmer.ygfstore.util;

import android.util.Log;

import com.neufmer.ygfstore.YGFApplication;

/**
 * Created by WangXing on 2019/7/30.
 */
public class LogHelper {

    public static void i(String tag,String msg){
        if(!YGFApplication.isDebug){
            Log.i(tag,msg);
        }
    }
    public static void d(String tag,String msg){
        if(!YGFApplication.isDebug){
            Log.d(tag,msg);
        }
    }
    public static void e(String tag,String msg){
        if(!YGFApplication.isDebug){
            Log.e(tag,msg);
        }
    }
}
