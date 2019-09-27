package com.neufmer.ygfstore.ui.password;

import android.annotation.SuppressLint;
import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import com.neufmer.ygfstore.R;
import com.neufmer.ygfstore.api.source.LoginModel;
import com.neufmer.ygfstore.toolbar.ToolbarViewModel;
import com.wangxing.code.mvvm.base.event.SingleLiveEvent;
import com.wangxing.code.mvvm.binding.command.BindingAction;
import com.wangxing.code.mvvm.binding.command.BindingCommand;
import com.wangxing.code.mvvm.binding.command.BindingConsumer;
import com.wangxing.code.mvvm.http.ApiCallBack;
import com.wangxing.code.mvvm.http.ResponseThrowable;
import com.wangxing.code.mvvm.http.util.RequestBodyUtil;
import com.wangxing.code.mvvm.utils.ContextUtils;
import com.wangxing.code.mvvm.utils.RegexUtils;
import com.wangxing.code.mvvm.utils.StringUtils;
import com.wangxing.code.mvvm.utils.ToastUtils;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by WangXing on 2019/6/18.
 */
public class ForgotPwdViewModel extends ToolbarViewModel<LoginModel> {

    private static final int VERIFY_CODE_EXPIRES = 60;
    private int mCountDownSeconds = VERIFY_CODE_EXPIRES;

    public SingleLiveEvent<Void> updatePwdSuccess = new SingleLiveEvent<>();

    public ObservableBoolean btnEnable = new ObservableBoolean(false);

    public ObservableField<String> phone = new ObservableField<>("");

    public ObservableField<String> sendVerCode = new ObservableField<>(ContextUtils.getContext().getString(R.string.activity_forgot_psw_verification_code));
    public ObservableBoolean sendVerCodeEnable = new ObservableBoolean(false);

    public ObservableField<String> verificationCode = new ObservableField<>("");

    public ObservableField<String> newPwd = new ObservableField<>("");

    //倒计时handler
    private CountDownHandler handler;

    public ForgotPwdViewModel(@NonNull Application application) {
        super(application);
        handler = new CountDownHandler(this);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        setTitleTextRes(R.string.forgot_pwd_activity_title);
    }


    /**
     * 发送验证码
     */
    public BindingCommand sendVerificationCode = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (RegexUtils.isMobileExact(phone.get())) {
                sendVerCodeEnable.set(false);
                Map<String, String> map = new HashMap<>();
                map.put("mobile", Objects.requireNonNull(phone.get()));
                request(model.postVerCode(RequestBodyUtil.getBody(map)), new ApiCallBack<Void>() {
                    @Override
                    protected void onSuccess(Void o, String message) {
                        updateCountDown();
                    }

                    @Override
                    protected void onFailed(ResponseThrowable exception) {
                    }

                });
            } else {
                ToastUtils.showShort(R.string.toast_content_2);
            }

        }
    });

    /**
     * 重置密码
     */
    public BindingCommand updatePwd = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Map<String, String> map = new HashMap<>();
            map.put("mobile", Objects.requireNonNull(phone.get()));
            map.put("password", Objects.requireNonNull(newPwd.get()));
            map.put("verificationCode", Objects.requireNonNull(verificationCode.get()));
            request(model.postResetpwd(RequestBodyUtil.getBody(map)), new ApiCallBack<Void>() {
                @Override
                protected void onSuccess(Void data, String message) {
                    updatePwdSuccess.call();
                }

                @Override
                protected void onFailed(ResponseThrowable exception) {
                }

            }, true);

        }
    });

    public BindingCommand<String> accountChange = new BindingCommand<String>(new BindingConsumer<String>() {
        @Override
        public void call(String s) {
            if (!StringUtils.isTrimEmpty(s)) {
                if (!sendVerCodeEnable.get()) {
                    sendVerCodeEnable.set(true);
                }
            } else {
                if (sendVerCodeEnable.get()) {
                    sendVerCodeEnable.set(false);
                }
            }
        }
    });

    public BindingCommand<String> textChange = new BindingCommand<String>(new BindingConsumer<String>() {
        @Override
        public void call(String s) {
            if (!StringUtils.isTrimEmpty(phone.get()) && !StringUtils.isTrimEmpty(verificationCode.get()) && !StringUtils.isTrimEmpty(s)) {
                btnEnable.set(true);
            } else {
                btnEnable.set(false);
            }
        }
    });

    @SuppressLint("HandlerLeak")
    private class CountDownHandler extends Handler {

        private WeakReference<ForgotPwdViewModel> mPresenterRef;

        public CountDownHandler(ForgotPwdViewModel viewModel) {
            mPresenterRef = new WeakReference<>(viewModel);
        }

        @Override
        public void handleMessage(Message msg) {
            ForgotPwdViewModel forgotPwdViewModel = mPresenterRef.get();
            if (forgotPwdViewModel != null) {
                forgotPwdViewModel.updateCountDown();
            }
        }
    }

    /**
     * 发送验证码倒计时
     */
    private void updateCountDown() {
        sendVerCode.set(ContextUtils.getContext().getString(R.string.activity_forgot_psw_verification_code_countdown, --mCountDownSeconds));
        if (mCountDownSeconds == 0) {
            mCountDownSeconds = VERIFY_CODE_EXPIRES;
            if (StringUtils.isTrimEmpty(phone.get())) {
                sendVerCodeEnable.set(false);
            } else {
                sendVerCodeEnable.set(true);
            }
            sendVerCode.set(ContextUtils.getContext().getString(R.string.activity_forgot_psw_verification_code));
        } else {
            if (handler == null) {
                handler = new CountDownHandler(this);
            }
            handler.sendEmptyMessageDelayed(0, 1000);
        }
    }

}

