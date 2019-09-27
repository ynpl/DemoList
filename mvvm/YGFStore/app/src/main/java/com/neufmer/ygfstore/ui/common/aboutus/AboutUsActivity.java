package com.neufmer.ygfstore.ui.common.aboutus;

import android.os.Bundle;

import com.neufmer.ygfstore.BR;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.databinding.ActivityAboutUsBinding;
import com.wangxing.code.mvvm.base.BaseActivity;
import com.wangxing.code.mvvm.utils.StatusBarUtils;

/**
 * Created by WangXing on 2019/6/19.
 */
public class AboutUsActivity extends BaseActivity<ActivityAboutUsBinding, AboutUsViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_about_us;
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
    }
}
