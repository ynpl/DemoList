package com.neufmer.ygfstore.ui.search_history.keyword;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.neufmer.ygfstore.BR;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.databinding.ActivitySearchHistoryBinding;
import com.wangxing.code.mvvm.base.BaseActivity;
import com.wangxing.code.mvvm.utils.StatusBarUtils;

public class SearchHistoryKeywordActivity extends BaseActivity<ActivitySearchHistoryBinding, SearchHistoryKeywordViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_search_history;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initView() {
        super.initView();
        StatusBarUtils.setStatusBar(this, false, false);
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.selectionUC.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                assert s != null;
                binding.keyword.setSelection(s.length());
            }
        });
    }
}
