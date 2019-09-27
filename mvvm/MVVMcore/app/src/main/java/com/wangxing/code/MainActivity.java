package com.wangxing.code;

import android.os.Bundle;

import com.wangxing.code.databinding.ActivityMainBinding;
import com.wangxing.mvvm.core.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainActivityViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
