package com.neufmer.ygfstore.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <读取Json文件的工具类>
 */

public class GetJsonDataUtil {


    //这是一个方法，其中filename是放在assets中的本地JSON文件名
    public static String getJson(String fileName, Context context) {
        //这个用来保存JSON格式字符串
        StringBuilder stringBuilder = new StringBuilder();
        AssetManager assetManager = context.getAssets();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();

    }
}

