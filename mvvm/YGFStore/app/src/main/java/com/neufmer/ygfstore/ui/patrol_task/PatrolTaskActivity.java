package com.neufmer.ygfstore.ui.patrol_task;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.bean.TaskBean;
import com.neufmer.ygfstore.databinding.ActivityPatrolTaskBinding;
import com.wangxing.code.mvvm.BR;
import com.wangxing.code.mvvm.base.BaseActivity;
import com.wangxing.code.mvvm.utils.StatusBarUtils;

/**
 * Created by WangXing on 2019/6/19.
 */
public class PatrolTaskActivity extends BaseActivity<ActivityPatrolTaskBinding, PatrolTaskViewModel> {

    private int taskId;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_patrol_task;
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
    public void initParam() {
        super.initParam();
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            taskId = mBundle.getInt("taskId", -1);
        }
    }

    @Override
    public void initData() {
        super.initData();
        viewModel.getTaskSingle(taskId);
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.setDataUC.observe(this, new Observer<TaskBean>() {
            @Override
            public void onChanged(@Nullable TaskBean data) {
                binding.setData(data);
            }
        });

        viewModel.multiStatusLayoutUI.showContent.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                binding.commonLayout.showContent();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
