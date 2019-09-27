package com.neufmer.ygfstore.ui.login;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.neufmer.ygfstore.Const;
import com.neufmer.ygfstore.api.source.LoginModel;
import com.neufmer.ygfstore.bean.LoginBean;
import com.neufmer.ygfstore.ui.main.MainActivity;
import com.neufmer.ygfstore.ui.password.ForgotPwdActivity;
import com.wangxing.code.mvvm.base.BaseViewModel;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.binding.command.BindingConsumer;
import com.wangxing.code.mvvm.http.ApiCallBack;
import com.wangxing.code.mvvm.http.ResponseThrowable;
import com.wangxing.code.mvvm.http.util.RequestBodyUtil;
import com.wangxing.code.mvvm.manager.CacheInfoManager;
import com.wangxing.code.mvvm.utils.ACache;
import com.wangxing.code.mvvm.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.RequestBody;

public class LoginViewModel extends BaseViewModel<LoginModel> {

    public ObservableBoolean loginBtnEnable = new ObservableBoolean(false);
    public ObservableField<String> account = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }


    public BindingCommand<String> textChange = new BindingCommand<String>(new BindingConsumer<String>() {
        @Override
        public void call(String s) {
            if (!StringUtils.isTrimEmpty(account.get()) && !StringUtils.isTrimEmpty(s)) {
                loginBtnEnable.set(true);
            } else {
                loginBtnEnable.set(false);
            }
        }
    });

    /**
     * 登录
     */
    public BindingCommand loginClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

            Map<String, String> map = new HashMap<>();
            map.put("mobile", Objects.requireNonNull(account.get()));
            map.put("password", Objects.requireNonNull(password.get()));
            RequestBody body = RequestBodyUtil.getBody(map);
            request(model.postLogin(body), new ApiCallBack<LoginBean>() {
                @Override
                protected void onSuccess(LoginBean loginBean, String message) {
                    CacheInfoManager.getInstance().saveUserId(String.valueOf(loginBean.getId()));
                    CacheInfoManager.getInstance().saveUserToken(loginBean.getToken());
                    CacheInfoManager.getInstance().saveKeyValue("expire", loginBean.getExpire().toString());
                    //缓存是否是组长信息
                    CacheInfoManager.getInstance().saveKeyValue("isPrimary",loginBean.isPrimary()?"1":"0");

                    startActivity(MainActivity.class);
                    finish();
                }

                @Override
                protected void onFailed(ResponseThrowable exception) {
                    if (exception.handleCode(Const.errorCode())) {
                        exception.toast();
                    }
                }

            },true);
        }
    });
    /**
     * 忘记密码
     */
    public BindingCommand forgetPassword = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(ForgotPwdActivity.class);
        }
    });

}
