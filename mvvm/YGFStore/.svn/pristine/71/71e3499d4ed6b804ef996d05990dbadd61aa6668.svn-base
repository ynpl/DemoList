package com.neufmer.ygfstore.ui.signature.storefront;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;

import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.databinding.ActivityStoreFrontSignatureBinding;
import com.neufmer.ygfstore.ui.sync.SyncTaskActivity;
import com.neufmer.ygfstore.view.SignatureView;
import com.wangxing.code.mvvm.BR;
import com.wangxing.code.mvvm.base.BaseActivity;
import com.wangxing.code.mvvm.utils.StatusBarUtils;

import java.io.File;
import java.io.IOException;

/**
 * 店面签字
 */
public class StoreFrontSignatureActivity extends BaseActivity<ActivityStoreFrontSignatureBinding, StoreFrontSignatureViewModel> {

    private String mTaskId;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_store_front_signature;
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
        binding.signature.setOnSignListener(new SignatureView.onSignListener() {
            @Override
            public void onSign(boolean flag) {
                if (flag) {
                    if (binding.imgTip.getVisibility() != View.GONE) {
                        binding.imgTip.setVisibility(View.GONE);
                    }
                    if (binding.textTip.getVisibility() != View.GONE) {
                        binding.textTip.setVisibility(View.GONE);
                    }
                } else {
                    binding.imgTip.setVisibility(View.VISIBLE);
                    binding.textTip.setVisibility(View.VISIBLE);
                }
            }
        });
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
        viewModel.clearSignatureUC.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                binding.signature.clear();
                binding.imgTip.setVisibility(View.VISIBLE);
                binding.textTip.setVisibility(View.VISIBLE);
            }
        });

        viewModel.confirmSignatureUC.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {

                if (binding.imgTip.getVisibility() == View.GONE) {
                    try {
                        String state = Environment.getExternalStorageState();
                        File rootDir = state.equals(Environment.MEDIA_MOUNTED) ?
                                Environment.getExternalStorageDirectory() : getCacheDir();
                        File folderDir = new File(rootDir.getAbsolutePath() + "/YGFStores");
                        String s = folderDir.getPath() + System.currentTimeMillis() + ".png";
                        binding.signature.save(s);
                        viewModel.saveSubmitParam(s, mTaskId);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Bundle bundle = new Bundle();
                bundle.putString("taskId", mTaskId);
                startActivity(SyncTaskActivity.class, bundle);
            }
        });

    }
}
