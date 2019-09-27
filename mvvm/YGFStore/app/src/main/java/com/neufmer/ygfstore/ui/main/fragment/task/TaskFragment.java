package com.neufmer.ygfstore.ui.main.fragment.task;


import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.neufmer.ygfstore.BR;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.databinding.FragmentTaskBinding;
import com.wangxing.code.mvvm.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends BaseFragment<FragmentTaskBinding, TaskFragmentViewModel> {

    public TaskFragment() {
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_task;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initView() {
        super.initView();
        binding.swipeRefresh.autoRefresh();
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.swipeRefreshUC.finishLoadMore.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                binding.swipeRefresh.finishLoadMore();
            }
        });
        viewModel.swipeRefreshUC.finishLoadMoreWithNoMore.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                binding.swipeRefresh.finishLoadMoreWithNoMoreData();
            }
        });
        viewModel.swipeRefreshUC.finishRefreshing.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                binding.swipeRefresh.finishRefresh();
            }
        });
        viewModel.swipeRefreshUC.finishRefreshingWithNoMore.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                binding.swipeRefresh.finishRefreshWithNoMoreData();
            }
        });
        viewModel.swipeRefreshUC.noMore.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                binding.swipeRefresh.setNoMoreData(true);
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
    }
}
