package com.neufmer.ygfstore.ui.main.fragment.history;


import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.neufmer.ygfstore.BR;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.bean.SelectDateBean;
import com.neufmer.ygfstore.databinding.FragmentHistoryBinding;
import com.neufmer.ygfstore.event.EventKeys;
import com.neufmer.ygfstore.ui.main.fragment.history.fragment.MyMissionFragment;
import com.wangxing.code.mvvm.base.BaseFragment;
import com.wangxing.code.mvvm.manager.CacheInfoManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends BaseFragment<FragmentHistoryBinding, HistoryFragmentViewModel> {

    List<MyMissionFragment> mFragmentList = new ArrayList<>();

    public HistoryFragment() {
        LiveEventBus.get()
                .with(EventKeys.LIVE_EVENT_RETURN_SELECTED_DATE, SelectDateBean.class)
                .observe(this, new Observer<SelectDateBean>() {
                    @Override
                    public void onChanged(@Nullable SelectDateBean data) {
//                        viewModel.date.set(b);
                        assert data != null;
                        viewModel.setSelectDateBean(data);
                        if(mFragmentList != null && mFragmentList.size() > 0){
                            for(MyMissionFragment f : mFragmentList){
                                f.setSelectDate(data);
                            }
                        }
                    }
                });
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_history;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initView() {
        super.initView();
        List<String> list = new ArrayList<>();
        //lt 用户是不是组长的不同UI处理
        if("1".equals(CacheInfoManager.getInstance().getValue("isPrimary"))){
            list.add(getResources().getString(R.string.team_mission));
            mFragmentList.add(new MyMissionFragment(2));
        }
        list.add(getResources().getString(R.string.my_mission));
        list.add(getResources().getString(R.string.store_mission));
        mFragmentList.add(new MyMissionFragment(1));
        mFragmentList.add(new MyMissionFragment(3));
        binding.vpFragmentHistory.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), list));
        //将TabLayout和ViewPager关联起来
        binding.topTabLayout.setupWithViewPager(binding.vpFragmentHistory);
        binding.vpFragmentHistory.setOffscreenPageLimit(3);
       
    }


    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public List<String> titleList;

        public ViewPagerAdapter(FragmentManager fm, List<String> titleList) {
            super(fm);
            this.titleList = titleList;
        }

        @Override
        public Fragment getItem(int i) {
            //lt
//            Fragment fragment = null;
//            switch (i) {
//                case 0:
//                    fragment = new MyMissionFragment();
//                    break;
//                case 1:
//                    fragment = new MyMissionFragment();
//                    break;
//                case 2:
//                    fragment = new MyMissionFragment();
//                    break;
//            }
//
//            return fragment;
            return mFragmentList.get(i);
        }

        @Override
        public int getCount() {
            return "1".equals(CacheInfoManager.getInstance().getValue("isPrimary"))?3:2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }

}
