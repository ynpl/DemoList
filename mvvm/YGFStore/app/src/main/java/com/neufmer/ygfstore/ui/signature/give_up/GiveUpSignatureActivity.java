package com.neufmer.ygfstore.ui.signature.give_up;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.neufmer.ygfstore.BR;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.databinding.ActivityGiveUpSignatureBinding;
import com.neufmer.ygfstore.ui.sync.SyncTaskActivity;
import com.wangxing.code.mvvm.base.BaseActivity;
import com.wangxing.code.mvvm.utils.StatusBarUtils;

/**
 * Created by WangXing on 2019/6/27.
 */
public class GiveUpSignatureActivity extends BaseActivity<ActivityGiveUpSignatureBinding, GiveUpSignatureViewModel> {

    private String mTaskId;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_give_up_signature;
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
    public void initParam() {
        super.initParam();
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        mTaskId = extras.getString("taskId");
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.setTaskId(mTaskId);
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.goSyncUC.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                Bundle bundle = new Bundle();
                bundle.putString("taskId", mTaskId);
                startActivity(SyncTaskActivity.class, bundle);
            }
        });
    }
}
