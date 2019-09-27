package com.neufmer.ygfstore.ui.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.RadioGroup;

import com.neufmer.ygfstore.BR;
import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.databinding.ActivityMainBinding;
import com.neufmer.ygfstore.ui.login.LoginActivity;
import com.neufmer.ygfstore.ui.main.fragment.history.HistoryFragment;
import com.neufmer.ygfstore.ui.main.fragment.mine.MineFragment;
import com.neufmer.ygfstore.ui.main.fragment.task.TaskFragment;
import com.wangxing.code.mvvm.base.BaseActivity;
import com.wangxing.code.mvvm.utils.StatusBarUtils;
import com.yanzhenjie.permission.AndPermission;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {


    @Override
    protected void doBeforeSetContentView() {
        super.doBeforeSetContentView();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setStatusBar(this, false, true);
        requestPermissions();
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void initView() {
        super.initView();
        binding.vpMainActivity.setNoScroll(true);
        binding.vpMainActivity.setOffscreenPageLimit(3);
        binding.rgMainActivity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_main_activity_bottom_bar_task) {
                    binding.vpMainActivity.setCurrentItem(0);
                    StatusBarUtils.setStatusTextColor(false, MainActivity.this);

                } else if (checkedId == R.id.rb_main_activity_bottom_bar_history) {
                    binding.vpMainActivity.setCurrentItem(1);
                    StatusBarUtils.setStatusTextColor(true, MainActivity.this);
                } else {
                    binding.vpMainActivity.setCurrentItem(2);
                    StatusBarUtils.setStatusTextColor(true, MainActivity.this);
                }
            }
        });

    }

    /**
     * 请求相机权限
     */
    @SuppressLint("CheckResult")
    private void requestPermissions() {
        AndPermission.with(this)
                .runtime()
                .permission(Manifest.permission.READ_EXTERNAL_STORAGE
                        , Manifest.permission.CAMERA
                        , Manifest.permission.ACCESS_COARSE_LOCATION
                        , Manifest.permission.ACCESS_FINE_LOCATION
                        , Manifest.permission.READ_PHONE_STATE
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                .onGranted(new Action<List<String>>() {
//                    @Override
//                    public void onAction(List<String> permissions) {
//                        toast(R.string.successfully);
//                    }
//                })
//                .onDenied(new Action<List<String>>() {
//                    @Override
//                    public void onAction(@NonNull List<String> permissions) {
//                        toast(R.string.failure);
//                        if (AndPermission.hasAlwaysDeniedPermission(MainActivity.this, permissions)) {
//                            showSettingDialog(MainActivity.this, permissions);
//                        }
//                    }
//                })
                .start();
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.renewUC.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                assert integer != null;
                if (integer == 401001) {
                    startActivity(LoginActivity.class);
                    finish();
                } else {
                    binding.vpMainActivity.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
                }
            }
        });

    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragment = null;
            switch (i) {
                case 0:
                    fragment = new TaskFragment();
                    break;
                case 1:
                    fragment = new HistoryFragment();
                    break;
                case 2:
                    fragment = new MineFragment();
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
