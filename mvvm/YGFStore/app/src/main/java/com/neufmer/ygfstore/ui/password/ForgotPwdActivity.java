package com.neufmer.ygfstore.ui.password;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.neufmer.ygfstore.BR;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.databinding.ActivityForgotPswBinding;
import com.wangxing.code.mvvm.base.BaseActivity;
import com.wangxing.code.mvvm.utils.StatusBarUtils;

/**
 * Created by WangXing on 2019/6/18.
 */
public class ForgotPwdActivity extends BaseActivity<ActivityForgotPswBinding, ForgotPwdViewModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_forgot_psw;
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

    @Override
    public void onBackPressed() {
        final MaterialDialog dialog = new MaterialDialog.Builder(ForgotPwdActivity.this).customView(R.layout.dialog_forgot_pwd_give_up, false).show();
        View view = dialog.getCustomView();
        assert view != null;
        view.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
        view.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.updatePwdSuccess.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                final MaterialDialog dialog = new MaterialDialog.Builder(ForgotPwdActivity.this).customView(R.layout.dialog_forgot_pwd_tip, false).show();
                View view = dialog.getCustomView();
                assert view != null;
                view.findViewById(R.id.know).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        finish();
                    }
                });

            }
        });
    }
}
