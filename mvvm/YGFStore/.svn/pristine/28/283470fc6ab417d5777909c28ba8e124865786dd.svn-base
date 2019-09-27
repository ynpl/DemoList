package com.neufmer.ygfstore.ui.login;

import android.os.Bundle;

import com.neufmer.ygfstore.BR;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.databinding.ActivityLoginBinding;
import com.wangxing.code.mvvm.base.BaseActivity;
import com.wangxing.code.mvvm.utils.StatusBarUtils;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    protected void doBeforeSetContentView() {
        super.doBeforeSetContentView();
        StatusBarUtils.setStatusBar(this, false, false);
    }
}
