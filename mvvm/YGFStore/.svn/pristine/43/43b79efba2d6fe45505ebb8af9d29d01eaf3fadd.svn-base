package com.neufmer.ygfstore.ui.password;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.neufmer.ygfstore.Const;
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
import com.wangxing.code.mvvm.utils.StringUtils;
import com.wangxing.code.mvvm.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by WangXing on 2019/6/19.
 */
public class ChangePwdViewModel extends ToolbarViewModel<LoginModel> {

    public ObservableField<String> oldPwd = new ObservableField<>("");
    public ObservableField<String> newPwd = new ObservableField<>("");
    public ObservableField<String> confirmNewPwd = new ObservableField<>("");
    public ObservableBoolean btnEnable = new ObservableBoolean(false);

    public SingleLiveEvent<Void> showDialog = new SingleLiveEvent<>();

    public ChangePwdViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        setTitleTextRes(R.string.change_pwd_activity_title);
    }

    public BindingCommand<String> textChange = new BindingCommand<String>(new BindingConsumer<String>() {
        @Override
        public void call(String s) {
            if (!StringUtils.isTrimEmpty(oldPwd.get()) && !StringUtils.isTrimEmpty(newPwd.get()) && !StringUtils.isTrimEmpty(s)) {
                btnEnable.set(true);
            } else {
                btnEnable.set(false);
            }
        }
    });

    public BindingCommand changePwd = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (Objects.equals(newPwd.get(), confirmNewPwd.get())) {
                //调用修改密码接口
                Map<String, String> map = new HashMap<>();
                map.put("oldPassword", Objects.requireNonNull(oldPwd.get()));
                map.put("newPassword", Objects.requireNonNull(newPwd.get()));
                request(model.postUpdatepwd(Const.header(), RequestBodyUtil.getBody(map)), new ApiCallBack<Void>() {
                    @Override
                    protected void onSuccess(Void updatePwdBean, String message) {
                        finish();
                        ToastUtils.showShort(R.string.toast_content_3);
                    }

                    @Override
                    protected void onFailed(ResponseThrowable exception) {
                    }
                });
            } else {
                //不一致显示tip dialog
                showDialog.call();
            }
        }
    });

}
