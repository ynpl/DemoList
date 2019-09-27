package com.neufmer.ygfstore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;

import com.neufmer.ygfstore.ui.login.LoginActivity;
import com.wangxing.code.mvvm.base.BaseApplication;
import com.wangxing.code.mvvm.crash.CaocConfig;
import com.wangxing.code.mvvm.manager.CacheInfoManager;
import com.wangxing.code.mvvm.utils.KLog;
import com.wangxing.code.mvvm.utils.StringUtils;
import com.wangxing.code.mvvm.utils.ToastUtils;

/**
 * Created by WangXing on 2019/6/11.
 */
public class YGFApplication extends BaseApplication {

    public static boolean isDebug = false;
    @Override
    public void onCreate() {
        super.onCreate();

        //是否开启打印日志
        KLog.init(BuildConfig.DEBUG);

        //初始化Toast显示位置
        ToastUtils.setGravity(Gravity.CENTER, 0, 0);

        initCrash();

        setHttpLoadingRes(R.layout.loading_layout);
        setHttpLoadingStyle(R.style.LoadingDialog);


    }


    /**
     * 初始化全局异常崩溃
     */
    private void initCrash() {
        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //背景模式,开启沉浸式
                .enabled(false) //是否启动全局异常捕获
                .showErrorDetails(true) //是否显示错误详细信息
                .showRestartButton(true) //是否显示重启按钮
                .trackActivities(true) //是否跟踪Activity
                .minTimeBetweenCrashesMs(2000) //崩溃的间隔时间(毫秒)
                .errorDrawable(R.mipmap.ic_launcher) //错误图标
//                .restartActivity(LoginActivity.class) //重新启动后的activity
//                .errorActivity(YourCustomErrorActivity.class) //崩溃后的错误activity
//                .eventListener(new YourCustomEventListener()) //崩溃后的错误监听
                .apply();
    }

    /**
     * 未登录返回true  登录返回false
     *
     * @param context
     * @return
     */
    public static boolean checkLogin(Context context) {
        boolean isLogin = StringUtils.isEmpty(StringUtils.null2Length0(CacheInfoManager.getInstance().getUserId()));
        if (isLogin) {
            Intent intent = new Intent(context, LoginActivity.class);
            if (!(context instanceof Activity)) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            context.startActivity(intent);
        }
        return isLogin;
    }

}
