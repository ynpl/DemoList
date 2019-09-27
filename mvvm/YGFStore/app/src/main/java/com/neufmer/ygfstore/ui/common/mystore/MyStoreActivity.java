package com.neufmer.ygfstore.ui.common.mystore;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.neufmer.ygfstore.BR;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.databinding.ActivityMyStoreBinding;
import com.wangxing.code.mvvm.base.BaseActivity;
import com.wangxing.code.mvvm.utils.StatusBarUtils;

/**
 * Created by WangXing on 2019/6/19.
 */
public class MyStoreActivity extends BaseActivity<ActivityMyStoreBinding, MyStoreViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_my_store;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initView() {
        super.initView();
        StatusBarUtils.setStatusBar(this, false, false);
        setSupportActionBar(binding.include.toolbar);
        viewModel.initToolbar();
        binding.commonLayout.showLoading();
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.statusLayoutUC.showContent.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                binding.commonLayout.showContent();
            }
        });

        viewModel.statusLayoutUC.showEmpty.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                binding.commonLayout.showEmpty();
            }
        });
    }
}
