package com.wangxing.mvvm.core.base;

/**
 * Created by WangXing on 2019/5/27.
 */
public interface InitOperation {

    /**
     * 初始化界面传递参数
     */
    void initParam();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 初始化view
     */
    void initView();

    /**
     * 初始化界面观察者的监听
     */
    void initViewObservable();

}
