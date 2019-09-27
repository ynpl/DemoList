package com.neufmer.ygfstore.ui.password;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.neufmer.ygfstore.BR;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.databinding.ActivityChangePswBinding;
import com.wangxing.code.mvvm.base.BaseActivity;
import com.wangxing.code.mvvm.utils.StatusBarUtils;

/**
 * Created by WangXing on 2019/6/19.
 */
public class ChangePwdActivity extends BaseActivity<ActivityChangePswBinding, ChangePwdViewModel> {

    private MaterialDialog dialog;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_change_psw;
    }

    @Override
    public void initView() {
        super.initView();
        setSupportActionBar(binding.include.toolbar);
        viewModel.initToolbar();
        StatusBarUtils.setStatusBar(this, false, false);
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.showDialog.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                if (dialog == null) {
                    dialog = new MaterialDialog.Builder(ChangePwdActivity.this).customView(R.layout.dialog_change_pwd_tip, false).show();
                    View customView = dialog.getCustomView();
                    assert customView != null;
                    customView.findViewById(R.id.know).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        }
                    });
                } else {
                    if (!dialog.isShowing()) {
                        dialog.show();
                    }
                }

            }
        });
    }
}
