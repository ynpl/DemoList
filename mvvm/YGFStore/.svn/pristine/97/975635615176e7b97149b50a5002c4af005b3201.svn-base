package com.neufmer.ygfstore.db.converter;

import android.arch.persistence.room.TypeConverter;

import com.wangxing.code.mvvm.utils.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangXing on 2019/7/12.
 */
public class ContentConverter {

    @TypeConverter
    public static List<String> revert(String contentParamEntityStr) {
        // 使用Gson方法把json格式的string转成List
        return GsonUtil.GsonToList(contentParamEntityStr, String[].class);

    }

    @TypeConverter
    public static String converter(List<String> contentParamEntityStr) {
        // 使用Gson方法把List转成json格式的string，便于我们用的解析
        return GsonUtil.GsonString(contentParamEntityStr);
    }


    @TypeConverter
    public static List<List<String>> revertInteger(String contentParamEntityStr) {
        // 使用Gson方法把json格式的string转成List
        List<List<String>> resultList = new ArrayList<>();
        List<String> strings = GsonUtil.GsonToList(contentParamEntityStr, String[].class);
        for (String string : strings) {
            List<String> list = GsonUtil.GsonToList(string, String[].class);
            resultList.add(list);
        }
        return resultList;

    }

    @TypeConverter
    public static String converterInteger(List<List<String>> contentParamEntityStr) {
        // 使用Gson方法把List转成json格式的string，便于我们用的解析
        List<String> strList = new ArrayList<>();
        for (List<String> list : contentParamEntityStr) {
            strList.add(GsonUtil.GsonString(list));
        }
        return GsonUtil.GsonString(strList);
    }

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        List<List<String>> lists = new ArrayList<>();
        lists.add(stringList);

        String s = GsonUtil.GsonString(lists);
        System.out.println(s);
        List<String> list = GsonUtil.GsonToList(s, String[].class);
        System.out.println(list);
        for (String s1 : list) {
            System.out.println(s1);
            System.out.println("list" + GsonUtil.GsonToList(s, String[].class));
        }

    }

}
