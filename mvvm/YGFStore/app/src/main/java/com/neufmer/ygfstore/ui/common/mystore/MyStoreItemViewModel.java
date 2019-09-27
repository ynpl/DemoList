package com.neufmer.ygfstore.ui.common.mystore;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.neufmer.ygfstore.bean.MyStoreBean;
import com.neufmer.ygfstore.bean.StoresBean;
import com.wangxing.code.mvvm.base.ItemViewModel;

/**
 * Created by WangXing on 2019/6/13.
 */
public class MyStoreItemViewModel extends ItemViewModel<MyStoreViewModel> {

    public StoresBean mData;

    public MyStoreItemViewModel(@NonNull MyStoreViewModel viewModel, StoresBean data) {
        super(viewModel);
        mData = data;
    }
}
