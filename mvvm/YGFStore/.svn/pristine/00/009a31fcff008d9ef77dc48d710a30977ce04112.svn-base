package com.neufmer.ygfstore.ui.main.fragment.history.fragment;


import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.neufmer.ygfstore.BR;
import com.neufmer.ygfstore.Const;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.bean.SelectDateBean;
import com.neufmer.ygfstore.databinding.FragmentMyMissionBinding;
import com.neufmer.ygfstore.ui.main.fragment.history.HistoryFragment;
import com.wangxing.code.mvvm.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyMissionFragment extends BaseFragment<FragmentMyMissionBinding, MyMissionFragmentViewModel> {

    //lt
    private int index = 0;

    @SuppressLint("ValidFragment")
    public MyMissionFragment(int index){
        super();
        this.index = index;
        LiveEventBus.get().with("TabClick").observe(this, new Observer<Object>() {
            @Override
            public void onChanged(@Nullable Object o) {
                String result = (String) o;
                if("leftTabClick".equals(result)){
                    viewModel.setTaskState(Const.COMPLETED);
                }else{
                    viewModel.setTaskState(Const.UNSUBMITED);
                }
            }
        });
    }
    public MyMissionFragment() {
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_my_mission;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.setIndex(index);
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
    public void setSelectDate(SelectDateBean data){
        viewModel.setSelectDate(data);
    }

}
