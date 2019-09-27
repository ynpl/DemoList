package com.neufmer.ygfstore.ui.common.aboutus;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.toolbar.ToolbarViewModel;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.utils.ContextUtils;
import com.wangxing.code.mvvm.utils.ToastUtils;

/**
 * Created by WangXing on 2019/6/19.
 */
public class AboutUsViewModel extends ToolbarViewModel {

    public ObservableField<String> versionName = new ObservableField<>();

    public AboutUsViewModel(@NonNull Application application) {
        super(application);
        PackageManager pm = ContextUtils.getContext().getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(ContextUtils.getContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        assert pi != null;
        versionName.set(pi.versionName);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        setTitleTextRes(R.string.about_us_activity_title);
    }

    /**
     * 去评分
     */
    public BindingCommand toScoreClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            ToastUtils.showShort("去评分");
        }
    });

}
