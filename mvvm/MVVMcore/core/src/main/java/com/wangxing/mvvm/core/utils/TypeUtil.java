package com.wangxing.mvvm.core.utils;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by WangXing on 2017/8/31.
 */

public class TypeUtil {

    public static <T> T getTypeObject(Object o, int i) {

        try {
            Type genericSuperclass = o.getClass().getGenericSuperclass();
            if (genericSuperclass instanceof Class) {
                return null;
            } else {
                Type[] types = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
                return ((Class<T>) types[i]).newInstance();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        return null;

    }

}
