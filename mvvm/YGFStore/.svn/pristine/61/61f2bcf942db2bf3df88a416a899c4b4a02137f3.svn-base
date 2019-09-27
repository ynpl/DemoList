package com.neufmer.ygfstore.ui.main.fragment.mine;


import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.neufmer.ygfstore.BR;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.bean.UserBean;
import com.neufmer.ygfstore.databinding.FragmentMineBinding;
import com.wangxing.code.mvvm.base.BaseFragment;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment<FragmentMineBinding, MineFragmentViewModel> {

    public MineFragment() {
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_mine;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initView() {
        super.initView();
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(binding.mineInclude.toolbar);
        viewModel.initToolbar();
        binding.commonLayout.showLoading();
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.setDataUC.observe(this, new Observer<UserBean>() {
            @Override
            public void onChanged(@Nullable UserBean userBean) {
                binding.setData(userBean);
                binding.commonLayout.showContent();
            }
        });
    }
}
